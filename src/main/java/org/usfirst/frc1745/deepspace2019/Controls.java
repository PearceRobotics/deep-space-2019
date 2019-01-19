/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class Controls {
    private Joystick leftJoystick;
    private Joystick rightJoystick;

    public Controls(Joystick leftJoystick, Joystick rightJoystick) {
        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;
    }

    public double getJoystickY(Joystick joystick, double deadzone) {
        double y = joystick.getY();
        if(Math.abs(y) < deadzone) {
            return 0.0;
        } else if(y > 0) {
                return y - deadzone;
        } else {
            return y + deadzone;
        }
    }

    public Joystick getLeftJoystick() {
        return this.leftJoystick;
    }

    public Joystick getRightJoystick() {
        return this.rightJoystick;
    }
}
