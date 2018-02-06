/*
 * Class: CustomIterativeRobot
 * Author: Robert Ciborowski, Creators of WPILib
 * Date: 01/02/2018
 * Description: A modification of WPILib's Iterative Robot, which splits up the robot
 *              based on iterations of each mode.
 *              Q: Why would you create a custom iterative robot?
 *              A: For more control, even if it's unnecessary to have at first.
 */

package org.usfirst.frc.team854.robot;

import edu.wpi.first.wpilibj.RobotBase;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class CustomIterativeRobot extends RobotBase {
	private boolean disabledIsInitialised;
	private boolean autonomousIsInitialised;
	private boolean teleopIsInitialised;
	private boolean testIsInitialised;

	/** The default constructor, which sets initialisation statuses.*/
	public CustomIterativeRobot() {
		disabledIsInitialised = false;
		autonomousIsInitialised = false;
		teleopIsInitialised = false;
		testIsInitialised = false;
	}

	/** This is like the main method of the robot. It takes care of all necessary
	 * looping in order for the iterative functionality of this class to happen.
	 */
	public void startCompetition() {
		HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Iterative);

		// Runs the (hopefully overridden) robot initialisation method.
		robotInit();

		// This tells the DS that the robot is ready to be enabled.
		HAL.observeUserProgramStarting();

		// This disables the live window, whatever that means.
		LiveWindow.setEnabled(false);

		// This is the running loop of the robot.
		while (true) {
			// This will wait for new data to arrive, so that the loop only runs
			// when something new occurs.
			m_ds.waitForData();
			
			// This will run the mode that is currently enabled.
			if (isDisabled()) {
				// This will initialise the disabled mode, if necessary.
				if (!disabledIsInitialised) {
					LiveWindow.setEnabled(false);
					disabledInit();
					disabledIsInitialised = true;
					autonomousIsInitialised = false;
					teleopIsInitialised = false;
					testIsInitialised = false;
				}
				HAL.observeUserProgramDisabled();
				disabledPeriodic();
			} else if (isTest()) {
				// This will initialise the test mode, if necessary.
				if (!testIsInitialised) {
					LiveWindow.setEnabled(true);
					testInit();
					testIsInitialised = true;
					autonomousIsInitialised = false;
					teleopIsInitialised = false;
					disabledIsInitialised = false;
				}
				HAL.observeUserProgramTest();
				testPeriodic();
			} else if (isAutonomous()) {
				// This will initialise the autonomous mode, if necessary.
				if (!autonomousIsInitialised) {
					LiveWindow.setEnabled(false);
					autonomousInit();
					autonomousIsInitialised = true;
					testIsInitialised = false;
					teleopIsInitialised = false;
					disabledIsInitialised = false;
				}
				HAL.observeUserProgramAutonomous();
				autonomousPeriodic();
			} else {
				// This will initialise the tele-operated mode, if necessary.
				if (!teleopIsInitialised) {
					LiveWindow.setEnabled(false);
					teleopInit();
					teleopIsInitialised = true;
					testIsInitialised = false;
					autonomousIsInitialised = false;
					disabledIsInitialised = false;
				}
				HAL.observeUserProgramTeleop();
				teleopPeriodic();
			}
			robotPeriodic();
		}
	}

	/** The robot's initialisation method, which should be overridden.*/
	public void robotInit() {
		System.out.println("You forgot to override robotInit()!");
	}

	/** The robot's disabled initialisation method, which should be overridden.*/
	public void disabledInit() {
		System.out.println("You forgot to override disabledInit()!");
	}

	/** The robot's autonomous initialisation method, which should be overridden.*/
	public void autonomousInit() {
		System.out.println("You forgot to override autonomousInit()!");
	}

	/** The robot's tele-operated initialisation method, which should be overridden.*/
	public void teleopInit() {
		System.out.println("You forgot to override teleopInit()!");
	}

	/** The robot's test initialisation method, which should be overridden.*/
	@SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
	public void testInit() {
		System.out.println("You forgot to override testInit()!");
	}


	private boolean robotPeriodicWasRunOnce = false;

	/** This is the robot's periodic method, which should be overridden. It runs no matter what the
	 * mode is. Note that it will run when a new packet is received from the driver station, which
	 * (according to WPILib's IterativeRobot) is approximately every 20 ms.
	 */
	public void robotPeriodic() {
		if (!robotPeriodicWasRunOnce) {
			System.out.println("You forgot to override robotPeriodic()!");
			robotPeriodicWasRunOnce = true;
		}
	}

	private boolean disabledPeriodicWasRunOnce = false;

	/** This is the robot's disabled periodic method, which should be overridden. It runs during the
	 * disabled mode. Note that it will run when a new packet is received from the driver station, which
	 * (according to WPILib's IterativeRobot) is approximately every 20 ms.
	 */
	public void disabledPeriodic() {
		if (!disabledPeriodicWasRunOnce) {
			System.out.println("You forgot to override disabledPeriodic()!");
			disabledPeriodicWasRunOnce = true;
		}
	}

	private boolean autonomousPeriodicWasRunOnce = false;

	/** This is the robot's autonomous periodic method, which should be overridden. It runs during the
	 * autonomous mode. Note that it will run when a new packet is received from the driver station, which
	 * (according to WPILib's IterativeRobot) is approximately every 20 ms.
	 */
	public void autonomousPeriodic() {
		if (!autonomousPeriodicWasRunOnce) {
			System.out.println("You forgot to override autonomousPeriodic()!");
			autonomousPeriodicWasRunOnce = true;
		}
	}

	private boolean teleopPeriodicWasRunOnce = false;

	/** This is the robot's tele-operated periodic method, which should be overridden. It runs during the
	 * tele-operated mode. Note that it will run when a new packet is received from the driver station, which
	 * (according to WPILib's IterativeRobot) is approximately every 20 ms.
	 */
	public void teleopPeriodic() {
		if (!teleopPeriodicWasRunOnce) {
			System.out.println("You forgot to override teleopPeriodic()!");
			teleopPeriodicWasRunOnce = true;
		}
	}

	private boolean testPeriodicWasRunOnce = false;

	/** This is the robot's test periodic method, which should be overridden. It runs during the
	 * test mode. Note that it will run when a new packet is received from the driver station, which
	 * (according to WPILib's IterativeRobot) is approximately every 20 ms.
	 */
	@SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
	public void testPeriodic() {
		if (!testPeriodicWasRunOnce) {
			System.out.println("You forgot to override testPeriodic()!");
			testPeriodicWasRunOnce = true;
		}
	}
}