/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;

/**
 * Add your docs here.
 */
public class NetworkOperations {
    private Preferences preferences;
    private static NetworkTableInstance networkTable;

    public NetworkOperations() {
        networkTable = networkTable.getDefault();
        networkTable.startClientTeam(1745);
        networkTable.startDSClient();
    }

    public static NetworkTable getLimelightNetworkTable(String networkTableName) {
        return networkTable.getDefault().getTable(networkTableName);
      }

    public double getPreferencesDouble(String label){
        return preferences.getDouble(label, 0.0);
    }

    public void setSmartDBNumVar(String label, double value){
        SmartDashboard.putNumber(label, value);
    }
}
