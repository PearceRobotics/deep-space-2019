/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class Ultrasonic {

    private AnalogInput analogInput;

    private final double VOLTS_TO_DISTANCE = .0097;

    public Ultrasonic(int analogPort) {
        this.analogInput = new AnalogInput(analogPort);
    }

    public double getInches() {
        return this.analogInput.getAverageVoltage() / VOLTS_TO_DISTANCE;
    }

    public boolean inRange(double inches) {
        return getInches() <= inches;
    }
}
