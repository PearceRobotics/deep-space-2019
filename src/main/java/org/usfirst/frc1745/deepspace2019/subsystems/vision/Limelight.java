/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.usfirst.frc1745.deepspace2019.subsystems.vision.TankDriveCommands;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {

    //do nothing
  }

  private double KpAim = -0.1f;
  private double KpDistance = -0.1f;
  private double min_aim_command = 0.05f;

  public double[] calcSpeed() {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    double tx = table.getEntry("tx").getDouble(0.0);
    double ty = table.getEntry("ty").getDouble(0.0);

    double headingError = -tx;
    double distanceError = -ty;
    double steeringAdjust = 0.0f;

    if (tx > 1.0) {
      steeringAdjust = KpAim * headingError - min_aim_command;
    } else if (tx < 1.0) {
      steeringAdjust = KpAim * headingError + min_aim_command;
    }

    double distanceAdjust = KpDistance * distanceError;

    double leftDelta = steeringAdjust + distanceAdjust;
    double rightDelta = -(steeringAdjust + distanceAdjust);

    return new double[]{leftDelta, rightDelta};
  
  }

  public double getKpAim() {
    return KpAim;
  }

  public double setKpAim(double KpAim) {
    this.KpAim = KpAim;
    return KpAim;
    
  }
}
