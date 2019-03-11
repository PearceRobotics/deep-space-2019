/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems;

import edu.wpi.first.wpilibj.Spark;

/**
 * Add your docs here.
 */
public class LedPWMController {
    private Spark blinkin;

    public LedPWMController(int channel) {
        this.blinkin = new Spark(0);
    }

    public void setColor(BlinkinColors color) {
       this.blinkin.set(color.getValue());
    }

    public enum BlinkinColors {
        LIME(0.73),
        PARTY(-0.97);
        private double value;
    
        BlinkinColors(double value) {
            this.value = value;
        }
    
        public double getValue() {
            return this.value;
        }
    }
}