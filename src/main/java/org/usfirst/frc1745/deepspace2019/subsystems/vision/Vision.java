
package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import org.usfirst.frc1745.deepspace2019.subsystems.LedPWMController;
import org.usfirst.frc1745.deepspace2019.subsystems.LedPWMController.BlinkinColors;
import org.usfirst.frc1745.deepspace2019.subsystems.drive.DrivingDeltas;

public class Vision {
    private Limelight limelight;
    private Ultrasonic ultrasonicLeft;
    private Ultrasonic ultrasonicRight;
    private LedPWMController ledPWMController;

    private final double IN_RANGE_INCHES = 24.0; 
    private final double ULTRASOUND_DEADBAND_INCHES = 2.0;

    public Vision(Limelight limelight, int leftUltrasonicPort, int rightUltrasonicPort, 
                    LedPWMController ledPWMController) {
        this.limelight = limelight;
        this.ultrasonicLeft = new Ultrasonic(leftUltrasonicPort);
        this.ultrasonicRight = new Ultrasonic(rightUltrasonicPort);
        this.ledPWMController = ledPWMController;
    }

    public DrivingDeltas targetDelta() {
        if(limelight.hasValidTargets()) {
            ledPWMController.setColor(BlinkinColors.LIME);
        } else {
            ledPWMController.setColor(BlinkinColors.PARTY);
        }
        
        System.out.println("ultrasonic values: L " + ultrasonicLeft.getInches() + " - R " + ultrasonicRight.getInches());
        
        //anything less than ~6.5 inches Maxbotix LV1 will report as ~6.5 inches
        boolean leftCloseEnough = ultrasonicLeft.inRange(7);
        boolean rightCloseEnough = ultrasonicRight.inRange(7);
        if(leftCloseEnough && rightCloseEnough) {
            System.out.println("Within 7 inches each side");
            return new DrivingDeltas();
        }else if(!ultrasonicLeft.inRange(IN_RANGE_INCHES) || !ultrasonicRight.inRange(IN_RANGE_INCHES)) {
            System.out.println("Left or right side outside of ");
            return limelight.calculateDeltas();
        } else if(Math.abs(ultrasonicLeft.getInches() - ultrasonicRight.getInches()) < ULTRASOUND_DEADBAND_INCHES) {
            System.out.println("parallel within 2 inches");
            return new DrivingDeltas(.1, 0);
        } else if (!leftCloseEnough && rightCloseEnough) {
            System.out.println("Left correction");
            return new DrivingDeltas(0, -.1);
        } else if (leftCloseEnough && !rightCloseEnough) {
            System.out.println("Right correction");
            return new DrivingDeltas(0, .1);
        } 

        return new DrivingDeltas();
    }
}
