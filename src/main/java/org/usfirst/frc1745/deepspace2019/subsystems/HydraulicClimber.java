/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems;

import org.usfirst.frc1745.deepspace2019.subsystems.drive.Drive;
import org.usfirst.frc1745.deepspace2019.subsystems.manipulator.DoubleSolenoidActuator;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */
public class HydraulicClimber {

    private final int FRONT_LEFT_DOUBLESOLENOID_FORWARD_PORT_ID = 2;
    private final int FRONT_RIGHT_DOUBLESOLENOID_REVERSE_PORT_ID = 3;
    private final int BACK_LEFT_DOUBLESOLENOID_FORWARD_PORT_ID = 4;
    private final int BACK_RIGHT_DOUBLESOLENOID_REVERSE_PORT_ID = 5;

    private DoubleSolenoidActuator frontDoubleSolenoid;
    private DoubleSolenoidActuator rearDoubleSolenoid;

    private Drive drive;

    public HydraulicClimber(Drive drive) {
        this.frontDoubleSolenoid = new DoubleSolenoidActuator(new DoubleSolenoid(FRONT_LEFT_DOUBLESOLENOID_FORWARD_PORT_ID, FRONT_RIGHT_DOUBLESOLENOID_REVERSE_PORT_ID));
        this.rearDoubleSolenoid = new DoubleSolenoidActuator(new DoubleSolenoid(BACK_LEFT_DOUBLESOLENOID_FORWARD_PORT_ID, BACK_RIGHT_DOUBLESOLENOID_REVERSE_PORT_ID));
        this.drive = drive;
        this.rearDoubleSolenoid.off();
        this.frontDoubleSolenoid.off();
    }

    public void pushFront(){
        this.frontDoubleSolenoid.toggle();
    }

    public void pushBack() {
        this.rearDoubleSolenoid.toggle();
    }

    public void climb() {
        this.frontDoubleSolenoid.toggle();
        Timer.delay(1);

        double timestamp = Timer.getFPGATimestamp();
        while(timestamp + 2 > Timer.getFPGATimestamp()) {
            drive.arcadeDrive(-.2, 0);
        }
        this.frontDoubleSolenoid.toggle();
        this.rearDoubleSolenoid.toggle();
        timestamp = Timer.getFPGATimestamp();
        while(timestamp + 2 > Timer.getFPGATimestamp()) {
            drive.arcadeDrive(-.2, 0);
        }
        this.rearDoubleSolenoid.toggle();
        Timer.delay(1);
    }
}
