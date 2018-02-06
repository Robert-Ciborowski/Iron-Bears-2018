/** 
 * Class: Motion
 * Author: Rana, Lucas, Cole, Robert Ciborowski, Danny Xu
 * Date: 03/02/2018
 * Description: A base class for moving the drive train in autonomous mode.
 */

package auto;

import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import hardware.Sensors;
import subsystems.ChassisSubsystem;

public class Motion extends Command implements RobotInterfaceConstants{
	private double distance, speed, angle, distanceRemaining;
	
	private Encoder leftEncoder = Sensors.leftEncoder, rightEncoder = Sensors.rightEncoder;
	private AnalogGyro gyro = Sensors.gyro;

	public Motion(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	

}
