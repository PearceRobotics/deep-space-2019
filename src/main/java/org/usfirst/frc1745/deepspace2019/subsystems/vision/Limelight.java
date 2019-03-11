/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import org.usfirst.frc1745.deepspace2019.NetworkOperations;
import org.usfirst.frc1745.deepspace2019.subsystems.drive.DrivingDeltas;

public class Limelight {
  /*
   * Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
   */
  private double horizontalTargetOffset = NetworkOperations.getNetworkTable("limelight").getEntry("tx").getDouble(0.0);
  /*
   * Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
   */
  private double verticalTargetOffset = NetworkOperations.getNetworkTable("limelight").getEntry("ty").getDouble(0.0);
  /*
   * Target Area (0% of image to 100% of image)
   */
  private double targetArea = NetworkOperations.getNetworkTable("limelight").getEntry("ty").getDouble(0.0);
  /*
   * Whether the limelight has any valid targets (0 or 1)
   */
  private boolean hasValidTargets = NetworkOperations.getNetworkTable("limelight").getEntry("ty").getDouble(0.0) < 1;
  
  
  // Constants to be adjusted for calculations
  private double kpAim = 0.015;
  private double kpDistance = 0.045;
  private final double DEADBAND_DEGREES = 2;

  // Uses NetworkTable values to calculate speed
  public DrivingDeltas calculateDeltas() {
    double steeringAdjust = 0;
    double distanceAdjust = 0;

    if(hasValidTargets) {
      if(horizontalTargetOffset < DEADBAND_DEGREES) {
        horizontalTargetOffset = 0;
      }

      if(verticalTargetOffset < DEADBAND_DEGREES) {
        verticalTargetOffset = 0;
      }
      
      steeringAdjust = kpAim * horizontalTargetOffset;
      distanceAdjust = kpDistance * verticalTargetOffset;
    }
    return new DrivingDeltas(distanceAdjust, steeringAdjust);
  }

  public boolean hasValidTargets() {
    return hasValidTargets;
  }
}