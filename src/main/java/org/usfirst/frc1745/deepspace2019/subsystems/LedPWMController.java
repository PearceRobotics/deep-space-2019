
package org.usfirst.frc1745.deepspace2019.subsystems;

import edu.wpi.first.wpilibj.Spark;

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