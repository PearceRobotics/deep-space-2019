
package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import org.usfirst.frc1745.deepspace2019.subsystems.drive.DrivingDeltas;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
  private final double KpAIM = 0.005;
  private final double KpDISTANCE = 0.025;
  private final double DEADBAND_DEGREES = 0;

  public DrivingDeltas calculateDeltas() {
    double steeringAdjust = 0;
    double distanceAdjust = 0;
    if(hasValidTarget()) {
      if(Math.abs(getHorizontalTargetOffset()) > DEADBAND_DEGREES) {
        steeringAdjust = KpAIM * getHorizontalTargetOffset();
      }

      if(Math.abs(getVerticalTargetOffset()) > DEADBAND_DEGREES) {
        distanceAdjust = KpDISTANCE * getVerticalTargetOffset();
      }
    }
    return new DrivingDeltas(-distanceAdjust, steeringAdjust);
  }

  /*
   * Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
   */
  public double getVerticalTargetOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
  }

  /*
   * Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
   */
  public double getHorizontalTargetOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
  }

  /*
   * Whether the limelight has any valid targets (0 or 1)
   */
  public boolean hasValidTarget() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0.0) > 0;
  }

  /*
   * Target Area (0% of image to 100% of image)
   */
  public double getTargetArea() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
  }
}