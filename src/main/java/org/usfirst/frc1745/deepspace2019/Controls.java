/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {
    private Joystick joystick;
    private final int LEFT_Y = 1;
    private final int RIGHT_Y = 5;
    private final int B_BUTTON = 2;
    private final int RIGHT_BUMPER = 6;

    public Controls(Joystick joystick) {
        this.joystick = joystick;
    }
    
    public boolean getBButton() {
        return joystick.getRawButton(B_BUTTON);
    }

    public boolean getRightBumper() {
        return joystick.getRawButton(RIGHT_BUMPER);
    }

    public double getLeftY(double deadzone) {
        double y = joystick.getRawAxis(LEFT_Y);
        if(Math.abs(y) < deadzone) {
            return 0.0;
        } 
        return y;
    }

    public double getRightY(double deadzone) {
        double y = joystick.getRawAxis(RIGHT_Y);
        if(Math.abs(y) < deadzone) {
            return 0.0;
        } 
        return y;
    }
}
