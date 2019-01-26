// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc1745.deepspace2019;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1745.deepspace2019.commands.*;
import org.usfirst.frc1745.deepspace2019.subsystems.drive.Drive;
import org.usfirst.frc1745.deepspace2019.subsystems.vision.Limelight;
import org.usfirst.frc1745.deepspace2019.subsystems.vision.TankDriveCommands;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();
    

    TankDriveCommands commands = new TankDriveCommands();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private Controls controls;
    private Drive drive;
    private Limelight limelight;

    private final int JOYSTICK_PORT = 0;

    private final double DEADZONE = 0.1;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        // (which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        this.drive = new Drive();
        this.limelight = new Limelight();
        this.controls = new Controls(new Joystick(JOYSTICK_PORT));
    }

    /**
     * This function is called when the disabled button is hit. You can use it to
     * reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        drive.setLeftSpeed((-controls.getLeftY(DEADZONE)));
        drive.setRightSpeed((controls.getRightY(DEADZONE)));
        /*commands = limelight.getTankDriveCommands(commands);

        SmartDashboard.putNumber("Limelight Left Command: ", commands.leftCommand);
        SmartDashboard.putNumber("Limelight Right Command: ", commands.rightCommand);
        SmartDashboard.putString("DB/String 0", "" + commands.leftCommand);
        SmartDashboard.putString("DB/String 1", "" + commands.rightCommand);*/

        double[] calculatedDeltas = limelight.calcSpeed();
        SmartDashboard.putNumber("Left Delta: ", calculatedDeltas[0]);
        SmartDashboard.putNumber("Right Delta ", calculatedDeltas[1]);

        SmartDashboard.putNumber("Aim Constant: ", limelight.getKpAim());
        limelight.setKpAim(SmartDashboard.getNumber("Aim Constant: ", limelight.getKpAim()));
        SmartDashboard.putNumber("Distance Constant: ", limelight.getKpDistance());
        limelight.setKpDistance(SmartDashboard.getNumber("Distance Constant", limelight.getKpDistance()));
        SmartDashboard.putNumber("Minimum Aim Constant: ", limelight.getMinAimCommand());
        limelight.setMinAimCommand(SmartDashboard.getNumber("Minimum Aim Constant: ", limelight.getMinAimCommand()));

    }
}
