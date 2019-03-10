/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems;

import edu.wpi.first.wpilibj.PWM;

/**
 * Add your docs here.
 */
public class LedPWMController {
    private PWM pwm;
    
    private final int GREEN_PULSE_WIDTH = 1865;
    private final int PARTY_PULSE_WIDTH = 1015;

    public LedPWMController(int channel) {
        this.pwm = new PWM(channel);
    }

    public void setGreenColor() {
        this.pwm.setRaw(GREEN_PULSE_WIDTH);
    }

    public void setPartyMode() {
        this.pwm.setRaw(PARTY_PULSE_WIDTH);
    }
}
