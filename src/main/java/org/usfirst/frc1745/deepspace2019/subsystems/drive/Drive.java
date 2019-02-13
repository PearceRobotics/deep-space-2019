/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class Drive {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public double kI;
  private Gearbox leftGearbox;
  private Gearbox rightGearbox;
  //left gear box CAN ids
  private final int LEFT_BACK_CAN_ID = 4;
  private final int LEFT_MIDDLE_CAN_ID = 5;
  private final int LEFT_FRONT_CAN_ID = 6;
  //right gear box CAN ids
  private final int RIGHT_BACK_CAN_ID = 11;
  private final int RIGHT_MIDDLE_CAN_ID = 12;
  private final int RIGHT_FRONT_CAN_ID = 13;

  private MotorType DRIVE_MOTOR_TYPE = MotorType.kBrushless;

  public Drive() {
    this.leftGearbox = new Gearbox(new CANSparkMax(LEFT_BACK_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(LEFT_MIDDLE_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(LEFT_FRONT_CAN_ID, DRIVE_MOTOR_TYPE));

    this.rightGearbox = new Gearbox(new CANSparkMax(RIGHT_BACK_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(RIGHT_MIDDLE_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(RIGHT_FRONT_CAN_ID, DRIVE_MOTOR_TYPE));
  }

  public void setLeftSpeed(double speed) {
    leftGearbox.setSpeed(speed);
  }

  public void setRightSpeed(double speed) {
    rightGearbox.setSpeed(speed);
  }

  public double setkI(double kI) {
    this.kI = kI;
    return this.kI;
  }

  public void arcadeDrive(double leftY, double rightX) {
    double error = (leftGearbox.getVelocity()-rightGearbox.getVelocity()) - rightX;
    double turnPower = error*rightX + kI;
    setLeftSpeed(-(leftY - turnPower));
    setRightSpeed(leftY + turnPower);
  }
}
