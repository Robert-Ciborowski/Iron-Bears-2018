package Autnomous;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;

import PID.DriveMotorPIDInput;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import subsystems.ChassisSubsystem;


/*Class: Motion 
 * Author: Rana, Lucas, Cole
 * Date:  2018-02-03
 * Description: Base class for moving the drivetrain in autonmous mode */




public class Motion extends Command implements RobotInterfaceConstants{
	
	private double distance;
	private double speed;
	private double angle;
	private double distanceRemaining;
	private Encoder leftEnc, rightEnc;
	private AnalogGyro gyro = ChassisSubsystem.getGyro();
	

	public Motion(double distance, double angle) {
		
		this.distance = distance;
		this.angle = angle;
		
		// init and start encoder counting
		
	}
	
	



	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
