/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {

    // do nothing
  }

  // Constants to be adjusted for calculations
  private double KpAim = -0.02;
  private double KpDistance = -0.02;
  private double min_aim_command = 0;
  private double deadband = 2.0;

  // Uses NetworkTable values to calculate speed
  public double[] calcSpeed(NetworkTable table) {

    double tx = table.getEntry("tx").getDouble(0.0);
    double ty = table.getEntry("ty").getDouble(0.0);

    double headingError = -tx;
    double distanceError = -ty;
    double steeringAdjust = 0.0f;

    if (tx > deadband) {
      steeringAdjust = (KpAim * headingError) - min_aim_command;
    } else if (tx < -deadband) {
      steeringAdjust = (KpAim * headingError) + min_aim_command;
    }

    double distanceAdjust = KpDistance * distanceError;

    double leftDelta = (-distanceAdjust + steeringAdjust);
    double rightDelta = (distanceAdjust + steeringAdjust);

    // Sets left and right delta values in an array
    return new double[] { leftDelta, rightDelta, steeringAdjust };

  }

  // Getters and setters
  // Aim Constant
  public double getKpAim() {
    return KpAim;
  }

  public double setKpAim(double KpAim) {
    this.KpAim = KpAim;
    return KpAim;
  }

  // Distance Contants
  public double getKpDistance() {
    return KpDistance;
  }

  public double setKpDistance(double KpDistance) {
    this.KpDistance = KpDistance;
    return KpDistance;
  }

  // Minimum Aim Constant
  public double getMinAimCommand() {
    return min_aim_command;
  }

  public double getDeadband() {
    return this.deadband;
  }

  public double setDeadband(double deadband) {
    this.deadband = deadband;
    return deadband;
  }

  public double setMinAimCommand(double min_aim_command) {
    this.min_aim_command = min_aim_command;
    return min_aim_command;
  }

  public double rightTargetSpeed(double rightDelta) {
    double rightTargetSpeed = rightDelta;
    return rightTargetSpeed;
  }

  public double leftTargetSpeed(double leftDelta) {
    double leftTargetSpeed = leftDelta;
    return leftTargetSpeed;
  }
}
