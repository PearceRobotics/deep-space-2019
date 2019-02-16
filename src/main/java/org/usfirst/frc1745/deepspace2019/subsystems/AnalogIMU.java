/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems;

import com.analog.adis16470.frc.ADIS16470_IMU;


/**
 * Add your docs here.
 */
public class AnalogIMU {

  private static ADIS16470_IMU imu;

  public AnalogIMU() {
    try {
      imu = new ADIS16470_IMU();
    } catch (RuntimeException ex ) {
      System.out.println("Error instantiating Analog IMU:  " + ex.getMessage());
    }
  }

  //Re-Zeroing the Sensor with reset()
  public void reset() {
    imu.reset();
  }

  public double getAngle() {
    return imu.getAngle();
  }
  
  public void calibrate() {
    imu.calibrate();
  }

  public ADIS16470_IMU getSensor() {
    return this.imu;
  }
}
