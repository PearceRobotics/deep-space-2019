
package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic {
    //LV-MaxSonar-EZ
    private AnalogInput analogInput;

    //[(Vcc/512) = Vi]
    //Vcc = Supplied Voltage, RoboRio 5v input
    //5/512 = 
    //Vi = Volts per inch (Scaling)
    private final double VOLTS_PER_INCH = 0.0098;

    public Ultrasonic(int analogPort) {
        this.analogInput = new AnalogInput(analogPort);
    }
    
    //[(Vm/Vi) = Ri]
    // Vm = Measured Voltage
    // Vi = Volts per Inch (Scaling)
    // Ri = Range in inches
    public double getInches() {
        return this.analogInput.getAverageVoltage() / VOLTS_PER_INCH;
    }

    public boolean inRange(double inches) {
        return getInches() <= inches;
    }
}
