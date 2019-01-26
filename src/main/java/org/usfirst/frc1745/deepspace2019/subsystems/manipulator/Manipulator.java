/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Manipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final int DOUBLESOLENOID_FORWARD_PORT_ID = 0;
  private final int DOUBLESOLENOID_REVERSE_PORT_ID = 1;

  private DoubleSolenoidActuator doubleSolenoid;
  
  public Manipulator() {
    this.doubleSolenoid = new DoubleSolenoidActuator(new DoubleSolenoid(DOUBLESOLENOID_FORWARD_PORT_ID, DOUBLESOLENOID_REVERSE_PORT_ID));
  }

  public void actuate(){
    this.doubleSolenoid.toggle();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
