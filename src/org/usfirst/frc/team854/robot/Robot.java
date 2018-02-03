/*
 * Class: Robot
 * Author: Robert Ciborowski, Waseef Nayeem and Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: A class which represents the robot as a whole. Consider it
 *              to be the main class.
 *              
 *              The VM is configured to automatically run this class, and to call the
 *              functions corresponding to each mode, as described in the IterativeRobot
 *              documentation. If you change the name of this class or the package after
 *              creating this project, you must also update the manifest file in the resource
 *              directory.
 */

package org.usfirst.frc.team854.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import oi.OperatorInterface;
import subsystems.ChassisSubsystem;

public class Robot extends CustomIterativeRobot {
	// These are the robot's subsystems.
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static List<PeriodicSubsystem> subsystemList = new ArrayList<PeriodicSubsystem>();
	
	// These represent other parts of the robot.
	public static OperatorInterface oi;
	public static PowerDistributionPanel pdp;

	Command autonomousCommand;

	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture("Front camera",0);
		pdp = new PowerDistributionPanel();
		oi = new OperatorInterface();

		subsystemList.add(chassisSubsystem);

		for (PeriodicSubsystem s : subsystemList) {
			s.init();
		}

		updateDashboard();
	}

	public void disabledInit() {
		updateDashboard();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}

	public void autonomousInit() {
		Robot.chassisSubsystem.reset();
		
		// autonomousCommand = new ...();
    	Scheduler.getInstance().add(autonomousCommand);
    	
        updateDashboard();
    }

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		updateDashboard();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void subsystemPeriodic() {
		// update all subsystem runtime data.
		for (PeriodicSubsystem r : subsystemList) {
			r.periodic();
		}
		oi.periodic();
	}

	private void updateDashboard() {
		// Need to work on updateDashboard!!!

		// update all subsystems and the OI dashboard items.
		for (PeriodicSubsystem r : subsystemList) {
			r.updateDashboard();
		}
		// oi.updateDashboard();

		SmartDashboard.putNumber("Joystick speed value", Robot.oi.getSpeed());
		SmartDashboard.putNumber("Joystick turn value", Robot.oi.getTurn());
	}
}
