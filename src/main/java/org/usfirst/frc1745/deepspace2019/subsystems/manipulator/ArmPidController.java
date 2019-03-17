/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.manipulator;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

public class ArmPidController {

  private CANSparkMax turnMotor;
  private CANPIDController turnController;

  private final double kP = 0.5;
  private final double kI = 0.0;
  private final double kD = 0.0;
  private final double kF = 0.0;

  public ArmPidController(CANSparkMax turnMotor) {
    this.turnMotor = turnMotor;
    this.turnController = turnMotor.getPIDController();
    this.turnController.setP(kP);
    this.turnController.setI(kI);
    this.turnController.setD(kD);
    this.turnController.setFF(kF);
    this.turnController.setOutputRange(-1, 1);
    this.turnMotor.setEncPosition(0);
  }

  public void retract() {
    turnController.setReference(0, ControlType.kPosition);
  }

  public void deploy() {
    turnController.setReference(-50, ControlType.kPosition);
  }
}