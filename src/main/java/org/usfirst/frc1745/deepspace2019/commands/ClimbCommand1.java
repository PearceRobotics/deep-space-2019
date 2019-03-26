/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.commands;

import org.usfirst.frc1745.deepspace2019.subsystems.HydraulicClimber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand1 extends Command {

  private HydraulicClimber hydraulicClimber;

  public ClimbCommand1(HydraulicClimber hydraulicClimber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.hydraulicClimber = hydraulicClimber;
    //requires(this.hydraulicClimber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    this.hydraulicClimber.pushFront();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
