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

import PID.DriveMotorPIDInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import oi.OperatorInterface;
import subsystems.ChassisSubsystem;
import utils.PIDSourceLogger;

public class Robot extends CustomIterativeRobot {
	// These are the robot's subsystems.
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static List<PeriodicSubsystem> subsystemList = new ArrayList<PeriodicSubsystem>();
	
	// These represent other parts of the robot.
	public static OperatorInterface oi;
	public static PowerDistributionPanel pdp;

	Command autonomousCommand;
	
	PIDSourceLogger logger;

	/** The robot's initialisation method.*/
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture("Front camera", 0);
		pdp = new PowerDistributionPanel();
		oi = new OperatorInterface();

		subsystemList.add(chassisSubsystem);

		for (PeriodicSubsystem s : subsystemList) {
			s.init();
		}
		logger = new PIDSourceLogger(DriveMotorPIDInput.gyro);

		updateDashboard();
	}
	
	@Override
	public void robotPeriodic() {
		// Useless function
	}

	/** This runs when the robot's disabled mode is enabled.*/
	public void disabledInit() {
		logger.output();
		updateDashboard();
	}

	/** This runs during the robot's disabled mode, periodically.*/
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}

	/** This runs when the robot's autonomous mode is enabled.*/
	public void autonomousInit() {
		Robot.chassisSubsystem.reset();
		
		// autonomousCommand = new ...();
    	Scheduler.getInstance().add(autonomousCommand);
    	
        updateDashboard();
    }

	/** This runs during the robot's autonomous mode, periodically.*/
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	/** This runs when the robot's disabled mode is enabled.*/
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		updateDashboard();
	}

	/** This runs during the robot's tele-operated mode, periodically.*/
	public void teleopPeriodic() {
		logger.log();
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}
	
	/** This runs when the robot's test mode is enabled.*/
	public void testInit() {
		updateDashboard();
	}

	/** This runs during the robot's disabled mode, periodically.*/
	public void testPeriodic() {
		LiveWindow.run();
	}

	/** This runs every subsystem's periodic method and should be called inside of
	 * an autonomous mode's periodic method.
	 */
	private void subsystemPeriodic() {
		// This updates all subsystem runtime data.
		for (PeriodicSubsystem r : subsystemList) {
			r.periodic();
		}
		oi.periodic();
	}

	/** This updates the FRC dashboard.*/
	private void updateDashboard() {
		// This updates all subsystem OI dashboard items.
		for (PeriodicSubsystem r : subsystemList) {
			r.updateDashboard();
		}
		oi.updateDashboard();
	}
}
