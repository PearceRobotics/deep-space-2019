/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.manipulator;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class DoubleSolenoidActuator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid doubleSolenoid;
  private DoubleSolenoid.Value state;

  public DoubleSolenoidActuator(DoubleSolenoid doubleSolenoid) {
    this.doubleSolenoid = doubleSolenoid;
    state = DoubleSolenoid.Value.kOff;
  }

  public void toggle() {
    switch(this.state) {
      case kOff: 
        this.state = DoubleSolenoid.Value.kForward;
        this.doubleSolenoid.set(this.state);
        break;
      case kForward:
        this.state = DoubleSolenoid.Value.kReverse;
        this.doubleSolenoid.set(this.state);
        break;
      case kReverse:
        this.state = DoubleSolenoid.Value.kForward;
        this.doubleSolenoid.set(this.state);
        break;
    }
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
