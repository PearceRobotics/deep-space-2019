/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.manipulator;

import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

public class ArmPidController {

  private CANPIDController turnController;

  //TODO: Make final once tuned
  private static double kP = 0.01;
  private static double kI = 0.0;
  private static double kD = 0.0;
  private static double kF = 0.0;

  public ArmPidController(CANPIDController turnController) {
    this.turnController = turnController;
    this.turnController.setP(kP);
    this.turnController.setI(kI);
    this.turnController.setD(kD);
    this.turnController.setFF(kF);
    this.turnController.setOutputRange(-1, 1);
  }

  public void deploy() {
    turnController.setReference(1, ControlType.kPosition);
  }

  public void retract() {
    turnController.setReference(-1, ControlType.kPosition);
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
}