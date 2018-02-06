package autonomous;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;

import PID.DriveMotorPIDInput;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import subsystems.ChassisSubsystem;

public class Movement extends Command implements RobotInterfaceConstants{
	// This class is used to move the robot during auto
	
	private double distance;
	private double speed;
	private double angle;
	private double distanceRemaining;
	Encoder leftEnc, rightEnc;
	// I made getGyro static -- Cole
	AnalogGyro gyro = DriveMotorPIDInput.getGyro();

	public Movement(double distance, double speed, double angle) {
		
		this.distance = distance;
		this.speed = speed;
		this.angle = angle;
		
		// read the gyro before
		
		// sets the angle the first time to turn it right away
		Robot.chassisSubsystem.setTargetMotion(this.angle, 10); // change speed as needed
		
		// TODO: Read from the gyro before, then after, and only init the encoders
		// when the turn is complete. Remember to account for a margin of error with gyro
		
		
		
		// init and start encoder counting
		leftEnc = new Encoder(PORT_ENCODER_LEFT_1, PORT_ENCODER_LEFT_2);
		rightEnc = new Encoder(PORT_ENCODER_RIGHT_1, PORT_ENCODER_RIGHT_2);
	}
	
	public double updateSpeed() {
		// calculates the remaining speed and returns the new speed needed
		
		double newSpeed = 0;
		distanceRemaining = distance - ((leftEnc.getDistance() + rightEnc.getDistance()) / 2);
		// rana writes his function here
		return newSpeed;
		
	}
	
	public void execute() {
		// updates the motors with the speed
		
		Robot.chassisSubsystem.setTargetMotion(0, updateSpeed());
	}
	
	public boolean isFinished() {
		// returns true when all the distance has been traveled
		
		if (distanceRemaining == 0) {
			return true;
		}
		return false;
	}
}
