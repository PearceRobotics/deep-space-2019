/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.drive;

import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax;

public class Gearbox {

  private CANSparkMax backController;
  private CANSparkMax middleController;
  private CANSparkMax frontController;

  public Gearbox(CANSparkMax backController, CANSparkMax middleController, CANSparkMax frontController) {
    this.backController = backController;
    this.middleController = middleController;
    this.frontController = frontController;
    this.setLeaderToFront();
  }

  private void setLeaderToFront() {
    /**
     * Slave controllers _only_ mirror the voltage, no other settings
     */
    CANError backSlave = this.backController.follow(this.frontController);
    // TODO - log error status
    CANError middleSlave = this.middleController.follow(this.frontController);
    // TODO - log error status
    if (backSlave != CANError.kOK || middleSlave != CANError.kOK) {
      throw new IllegalStateException("Unsucessful in setting leader. BackSlave error status: " + backSlave.name()
          + " MiddleSlave error status: " + middleSlave.name());
    }
  }

  public void setSpeed(double rate) {
    if (rate < -1.0) {
      rate = -1.0;
    } else if (rate > 1.0) {
      rate = 1.0;
    }
    this.frontController.set(rate);
  }

  public CANSparkMax getBackController() {
    return this.backController;
  }

  public CANSparkMax getMiddleController() {
    return this.middleController;
  }

  public CANSparkMax getFrontController() {
    return this.frontController;
  }

}
