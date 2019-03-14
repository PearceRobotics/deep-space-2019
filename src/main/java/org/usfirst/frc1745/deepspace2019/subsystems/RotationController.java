/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems;

import com.analog.adis16470.frc.ADIS16470_IMU;
import com.analog.adis16470.frc.ADIS16470_IMU.Axis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SPI;


public class RotationController implements PIDOutput {

  private PIDController turnController;

  //TODO: Make final once tuned
  private static double kP = 0.1;
  private static double kI = 0.0;
  private static double kD = 0.0;
  private static double kF = 0.0;
  //This tuning parameter indicates how close to "on target" the PID Controller will attempt to get.
  private static final double kToleranceDegrees = 2f;
  //Set by controller periodically
  private double rotateToAngleRate;
  private ADIS16470_IMU imu;

  public RotationController() {
    this.imu = new ADIS16470_IMU();
    turnController = new PIDController(kP, kI, kD, kF, this.imu, this);
    turnController.setInputRange(-180.0f,  180.0f);
    turnController.setOutputRange(-1.0, 1.0);
    turnController.setAbsoluteTolerance(kToleranceDegrees);
    turnController.setContinuous(true);
    imu.reset();
    imu.calibrate();
  }

  public void setSetPoint(double angle) {
    turnController.setSetpoint(angle);
    turnController.enable();
  }

  public void disable() {
    turnController.disable();
  }

  public boolean isEnabled() {
    return turnController.isEnabled();
  }

  @Override
  public void pidWrite(double output) {
    rotateToAngleRate = output;
  }

  public double getRotateToAngleRate() {
    return this.rotateToAngleRate;
  }

  public static double getKP() {
    return kP;
  }

  public static void setKp(double newKP) {
    kP = newKP;
  }

  public static double getKI() {
    return kI;
  }

  public static void setKI(double newKI) {
    kI = newKI;
  }

  public static double getKD() {
    return kD;
  }

  public static void setKD(double newKD) {
    kD = newKD;
  }

  public static double getKF() {
    return kF;
  }

  public static void setKF(double newKF) {
    kF = newKF;
  } 

  public void reset(){
    imu.reset();
  }

  public double getAngle(){
    // return imu.getSensor().getAngleZ();
    return imu.getAngle();
  }
}
