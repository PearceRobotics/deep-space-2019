/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;

/**
 * Add your docs here.
 */
public class NetworkOperations {
    private static Preferences preferences;
    private static NetworkTableInstance networkTable;

    public NetworkOperations() {
        networkTable = networkTable.getDefault();
        networkTable.startClientTeam(1745);
        networkTable.startDSClient();
        preferences = Preferences.getInstance();
    }

    public static NetworkTable getNetworkTable(String networkTableName) {
        return networkTable.getTable(networkTableName);
    }

    public static double getPreferencesDouble(String label) {
        return preferences.getDouble(label, 0.0);
    }

    public static void setSmartDBNumVar(String label, double value) {
        SmartDashboard.putNumber(label, value);
    }
}
