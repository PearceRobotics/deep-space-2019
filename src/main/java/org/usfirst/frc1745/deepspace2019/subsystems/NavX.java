/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */
public class NavX {

  private AHRS ahrs;

  public NavX() {
    try {
      ahrs = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
      System.out.println("Error instantiating navX-MXP:  " + ex.getMessage());
    }
  }

  public void resetYaw() {
    ahrs.zeroYaw();
  }

  public float getYaw() {
    return ahrs.getYaw();
  }

  public AHRS getAHRS() {
    return this.ahrs;
  }
}
