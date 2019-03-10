/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.vision;

import org.usfirst.frc1745.deepspace2019.subsystems.LedPWMController;

import edu.wpi.first.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class Vision {
    private Limelight limelight;
    private Ultrasonic ultrasonicLeft;
    private Ultrasonic ultrasonicRight;
    private LedPWMController ledPWMController;
    private NetworkTable limelightNetworkTableReference;

    private final double IN_RANGE = 24.0; 

    public Vision(Limelight limelight, int leftUltrasonicPort, int rightUltrasonicPort, 
                    LedPWMController ledPWMController,
                    NetworkTable limelightNetworkTableReference) {
        this.limelight = limelight;
        this.ultrasonicLeft = new Ultrasonic(leftUltrasonicPort);
        this.ultrasonicRight = new Ultrasonic(rightUltrasonicPort);
        this.ledPWMController = ledPWMController;
        this.limelightNetworkTableReference = limelightNetworkTableReference;
    }

    public double[] targetDelta() {
        double tx = limelightNetworkTableReference.getEntry("tx").getDouble(0.0);
        double ty = limelightNetworkTableReference.getEntry("ty").getDouble(0.0);

        if(tx != 0.0 || ty != 0.0) {
            ledPWMController.setGreenColor();
        } else {
            ledPWMController.setPartyMode();
        }
        
        System.out.println("ultrasonic" + ultrasonicLeft.getInches() + " " + ultrasonicRight.getInches());
        boolean leftCloseEnough = ultrasonicLeft.inRange(7);
        boolean rightCloseEnough = ultrasonicRight.inRange(7);
        if(leftCloseEnough && rightCloseEnough) {
            System.out.println("close enough");
            return new double[]{0, 0};
        }else if(!ultrasonicLeft.inRange(IN_RANGE) || !ultrasonicRight.inRange(IN_RANGE)) {
            System.out.println("lime");
            return limelight.calcSpeed(tx, ty);

        } else if(Math.abs(ultrasonicLeft.getInches() - ultrasonicRight.getInches()) < 2) {
            System.out.println("we parallel");
            return new double[]{-.1, .1};
        } else if (!leftCloseEnough && rightCloseEnough) {
            System.out.println("left");
            return new double[]{.1, 0};
        } else if (leftCloseEnough && !rightCloseEnough) {
            System.out.println("rigth");
            return new double[]{0, .1};
        } 

        return new double[]{0, 0};
    }
}
