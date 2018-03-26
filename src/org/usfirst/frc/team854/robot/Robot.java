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

import org.usfirst.frc.team854.robot.auto.AutoCommandGroup;
import org.usfirst.frc.team854.robot.auto.AutoConfig;
import org.usfirst.frc.team854.robot.auto.AutoLeftCommandGroup;
import org.usfirst.frc.team854.robot.auto.AutoMiddleCommandGroup;
import org.usfirst.frc.team854.robot.auto.AutoRightCommandGroup;
import org.usfirst.frc.team854.robot.auto.FieldTarget;
import org.usfirst.frc.team854.robot.auto.Position1D;
import org.usfirst.frc.team854.robot.auto.SimpleAutoCommandGroup;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.DeviceProvider;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;
import org.usfirst.frc.team854.robot.operatorinterface.OperatorInterface;
import org.usfirst.frc.team854.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team854.robot.subsystems.ArmSubsystemWithoutPID;
import org.usfirst.frc.team854.robot.subsystems.ChassisSubsystem;
import org.usfirst.frc.team854.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team854.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team854.robot.utils.PIDSourceLogger;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CustomIterativeRobot {
	// These are the subsystems of the robot.
	public static ChassisSubsystem chassisSubsystem;
//	public static ArmSubsystem armSubsystem;
	public static ArmSubsystemWithoutPID armSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static ClimberSubsystem climberSubsystem;

	// This provides all devices to the robot.
	public static DeviceProvider devices;

	// These represent other parts of the robot.
	public static OperatorInterface oi;
	public static PowerDistributionPanel pdp;

	private AutoCommandGroup autonomousCommand;

	private PIDSourceLogger logger;

	private SendableChooser<Position1D> locationChooser;
	private SendableChooser<FieldTarget> targetChooser;
	
	public static RobotMode currentMode;

	private void initDevices() {
		devices = new DeviceProvider();
		
		// This is where device initialisation begins.
		// These are the analog devices.
		devices.putDevice(InterfaceType.ANALOG, RobotInterfaceConstants.PORT_GYRO,
				new AnalogGyro(RobotInterfaceConstants.PORT_GYRO));
		
		// These are the digital devices.
		devices.putDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_LEFT,
				new Encoder(RobotInterfaceConstants.PORT_ENCODER_LEFT, RobotInterfaceConstants.PORT_ENCODER_LEFT_2));
		devices.putDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_RIGHT,
				new Encoder(RobotInterfaceConstants.PORT_ENCODER_RIGHT, RobotInterfaceConstants.PORT_ENCODER_RIGHT_2));
		devices.putDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM,
				new Encoder(RobotInterfaceConstants.PORT_ENCODER_ARM, RobotInterfaceConstants.PORT_ENCODER_ARM_2));
		//devices.putDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_SWITCH_ARM_HOME,
			//	new DigitalInput(RobotInterfaceConstants.PORT_SWITCH_ARM_HOME));
		devices.putDevice(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_SWITCH_INTAKE_FULL,
				new DigitalInput(RobotInterfaceConstants.PORT_SWITCH_INTAKE_FULL));

		// These are the PWM devices.
		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_DRIVE_LEFT,
				new Spark(RobotInterfaceConstants.PORT_MOTOR_DRIVE_LEFT));
		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_DRIVE_RIGHT,
				new Spark(RobotInterfaceConstants.PORT_MOTOR_DRIVE_RIGHT));
		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_ARM,
				new Spark(RobotInterfaceConstants.PORT_MOTOR_ARM));
		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_LEFT,
				new Spark(RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_LEFT));
		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_RIGHT,
				new Spark(RobotInterfaceConstants.PORT_MOTOR_INTAKE_INNER_RIGHT));
		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_LEFT,
				new Spark(RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_LEFT));
		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_RIGHT,
				new Spark(RobotInterfaceConstants.PORT_MOTOR_INTAKE_OUTER_RIGHT));
//		devices.putDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_CLIMBER,
//				new Spark(RobotInterfaceConstants.PORT_MOTOR_CLIMBER));
		
		// These are the PCM devices.
		devices.putDevice(InterfaceType.PCM, RobotInterfaceConstants.PORT_PNEUMATIC_LEFT,
				new DoubleSolenoid(RobotInterfaceConstants.PORT_PCM,
						RobotInterfaceConstants.PORT_PNEUMATIC_LEFT,
						RobotInterfaceConstants.PORT_PNEUMATIC_LEFT_REVERSE));
		devices.putDevice(InterfaceType.PCM, RobotInterfaceConstants.PORT_PNEUMATIC_RIGHT,
				new DoubleSolenoid(RobotInterfaceConstants.PORT_PCM,
						RobotInterfaceConstants.PORT_PNEUMATIC_RIGHT,
						RobotInterfaceConstants.PORT_PNEUMATIC_RIGHT_REVERSE));
		
		// This is our MXP device, an ADIS16448 gyro. It automatically takes the next 4 seconds to calibrate,
		// blocking changes in other WPILib devices.
		ADIS16448_IMU gyro = new ADIS16448_IMU();	
		devices.putDevice(InterfaceType.MXP, RobotInterfaceConstants.PORT_GYRO, gyro);

		chassisSubsystem = new ChassisSubsystem();
		chassisSubsystem.setEnabled(true);
		
//		armSubsystem = new ArmSubsystem();
		armSubsystem = new ArmSubsystemWithoutPID();
		armSubsystem.setEnabled(true);

		intakeSubsystem = new IntakeSubsystem();
		intakeSubsystem.setEnabled(true);
		
		climberSubsystem = new ClimberSubsystem();
		climberSubsystem.setEnabled(true);

		oi = new OperatorInterface();
		pdp = new PowerDistributionPanel();
	}

	/**
	 * The robot's initialisation method.
	 */
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture("Front camera", 0);
		initDevices();
		
		initDashboard();
		
		logger = new PIDSourceLogger(InterfaceType.DIGITAL, RobotInterfaceConstants.PORT_ENCODER_ARM);

		updateDashboard();
	}
	
	private void initDashboard() {
		locationChooser = new SendableChooser<>();
		locationChooser.addDefault("Left", Position1D.LEFT);
		locationChooser.addObject("Middle", Position1D.NEUTRAL);
		locationChooser.addObject("Right", Position1D.RIGHT);

		targetChooser = new SendableChooser<>();
		targetChooser.addDefault("Switch", FieldTarget.LOCAL_SWITCH);
		targetChooser.addObject("Scale", FieldTarget.SCALE);
		targetChooser.addObject("None", FieldTarget.NONE);

		SmartDashboard.putBoolean("over_extend", true);
		SmartDashboard.putBoolean("run_good_auto", true);
		SmartDashboard.putBoolean("two_cube", false);

		SmartDashboard.putData("Location", locationChooser);
		SmartDashboard.putData("Target", targetChooser);
	}
	
	@Override
	public void robotPeriodic() {
		// Useless function
	}

	/** This runs when the robot's disabled mode is enabled.*/
	public void disabledInit() {
		currentMode = RobotMode.DISABLED;
		chassisSubsystem.setCurrentMode(RobotMode.DISABLED);
		armSubsystem.setCurrentMode(RobotMode.DISABLED);
		intakeSubsystem.setCurrentMode(RobotMode.DISABLED);
		climberSubsystem.setCurrentMode(RobotMode.DISABLED);
		oi.setCurrentMode(RobotMode.DISABLED);
		// logger.output();
		updateDashboard();
	}

	/** This runs during the robot's disabled mode, periodically.*/
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}

	/**
	 * This runs when the robot's autonomous mode is enabled.
	 */
	public void autonomousInit() {
		currentMode = RobotMode.AUTONOMOUS;
		chassisSubsystem.setCurrentMode(RobotMode.AUTONOMOUS);
		armSubsystem.setCurrentMode(RobotMode.AUTONOMOUS);
		intakeSubsystem.setCurrentMode(RobotMode.AUTONOMOUS);
		climberSubsystem.setCurrentMode(RobotMode.AUTONOMOUS);
		oi.setCurrentMode(RobotMode.AUTONOMOUS);
		
		chassisSubsystem.resetTargetAngle();

//		// we have two versions of our auto mode
//		Position1D position = locationChooser.getSelected();
//		AutoConfig config = new AutoConfig.Builder(DriverStation.getInstance().getGameSpecificMessage())
//				.fieldTarget(targetChooser.getSelected())
//				.startingLocation(position)
//				.overextend(SmartDashboard.getBoolean("over_extend", true))
//				.build();
//
//		if (!SmartDashboard.getBoolean("run_good_auto", false)) {
//			position = null;
//		}
//		
//		System.out.println("Position: " + position);
//		System.out.println("Config: " + config.getStartingLocation() + ", " + config.getFieldTarget() + ", " + config.getPositionForTarget(FieldTarget.LOCAL_SWITCH));
//
//		autonomousCommand = getAutoCommand(position);
//		autonomousCommand.configure(config);
//    	Scheduler.getInstance().add(autonomousCommand);
		
		AutoCommandGroup simple = new SimpleAutoCommandGroup();
		simple.configure(null);
		Scheduler.getInstance().add(simple);

        updateDashboard();
    }
	
	private AutoCommandGroup getAutoCommand(Position1D position) {
		if (position == null) {
			return new SimpleAutoCommandGroup();
		}
		
		switch (position) {
			case LEFT:
				return new AutoLeftCommandGroup();
			case NEUTRAL:
				return new AutoMiddleCommandGroup();
			case RIGHT:
				return new AutoRightCommandGroup();
			default:
				throw new IllegalStateException("Stop messing with the JVM.");
		}
	}

	/**
	 * This runs during the robot's autonomous mode, periodically.
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		subsystemPeriodic();
		updateDashboard();
	}

	/**
	 * This runs when the robot's disabled mode is enabled.
	 */
	public void teleopInit() {
		currentMode = RobotMode.TELEOPERATED;
		chassisSubsystem.setCurrentMode(RobotMode.TELEOPERATED);
		armSubsystem.setCurrentMode(RobotMode.TELEOPERATED);
		intakeSubsystem.setCurrentMode(RobotMode.TELEOPERATED);
		climberSubsystem.setCurrentMode(RobotMode.TELEOPERATED);
		oi.setCurrentMode(RobotMode.TELEOPERATED);
		
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
		currentMode = RobotMode.TEST;
		chassisSubsystem.setCurrentMode(RobotMode.TEST);
		armSubsystem.setCurrentMode(RobotMode.TEST);
		intakeSubsystem.setCurrentMode(RobotMode.TEST);
		climberSubsystem.setCurrentMode(RobotMode.TEST);
		oi.setCurrentMode(RobotMode.TEST);
		updateDashboard();
	}

	/** This runs during the robot's disabled mode, periodically.*/
	public void testPeriodic() {
		// LiveWindow.run();
	}

	/**
	 * This runs every subsystem's periodic method.
	 */
	private void subsystemPeriodic() {
		chassisSubsystem.periodic();
		intakeSubsystem.periodic();
		armSubsystem.periodic();
		climberSubsystem.periodic();
	}

	/** This updates the FRC dashboard.*/
	private void updateDashboard() {
		// This updates all subsystem OI dashboard items.
		chassisSubsystem.updateDashboard();
		intakeSubsystem.updateDashboard();
		armSubsystem.updateDashboard();
		climberSubsystem.updateDashboard();
		oi.updateDashboard();
	}
}
