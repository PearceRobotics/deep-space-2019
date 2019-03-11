/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class Limelight {
  
  // Constants to be adjusted for calculations
  private double kpAim = -0.015;
  private double kpDistance = -0.045;
  private double minAimCommand = 0;
  private double deadband = 1.5;

  // Uses NetworkTable values to calculate speed
  public double[] calcSpeed(NetworkTable table) {

    double tx = table.getEntry("tx").getDouble(0.0);
    double ty = table.getEntry("ty").getDouble(0.0);

    double headingError = -tx;
    double distanceError = -ty;
    double steeringAdjust = 0.0f;

    if (tx > deadband) {
      steeringAdjust = (kpAim * headingError) - minAimCommand;
    } else if (tx < -deadband) {
      steeringAdjust = (kpAim * headingError) + minAimCommand;
    }

    double distanceAdjust = kpDistance * distanceError;

    double leftDelta = (-distanceAdjust + steeringAdjust);
    double rightDelta = (distanceAdjust + steeringAdjust);

    // Sets left and right delta values in an array
    return new double[] { leftDelta, rightDelta, steeringAdjust };
  }

  // Getters and setters
  // Aim Constant
  public double getKpAim() {
    return this.kpAim;
  }

  public double setKpAim(double kpAim) {
    this.kpAim = kpAim;
    return this.kpAim;
  }

  // Distance Contants
  public double getKpDistance() {
    return this.kpDistance;
  }

  public double setKpDistance(double kpDistance) {
    this.kpDistance = kpDistance;
    return this.kpDistance;
  }

  // Minimum Aim Constant
  public double getMinAimCommand() {
    return this.minAimCommand;
  }

  public double setMinAimCommand(double minAimCommand) {
    this.minAimCommand = minAimCommand;
    return this.minAimCommand;
  }
  
  public double getDeadband() {
    return this.deadband;
  }

  public double setDeadband(double deadband) {
    this.deadband = deadband;
    return this.deadband;
  }
}
