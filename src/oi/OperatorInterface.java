/**
 * Name: OperatorInterface
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Describes the user interface of the operator of the robot which includes many data values
 */

package oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;

public class OperatorInterface {
	private Joystick joystick = new Joystick(RobotInterfaceConstants.PORT_JOYSTICK);
	
	private Button driveReverse = new JoystickButton(joystick, 9);

	private Filter turningFilter = new CompoundFilter.Builder()
			.addFilter(new LinearFilter(UserInterfaceConstants.JOYSTICK_TURNING_OFFSET))
			.addFilter(new LogarithmicFilter(Math.PI / 2))
			.addFilter(new CutoffFilter(UserInterfaceConstants.JOYSTICK_TURNING_CUTOFF))
			.build();
	private Filter speedFilter = new CompoundFilter.Builder()
			.addFilter(new LinearFilter(UserInterfaceConstants.JOYSTICK_SPEED_OFFSET))
			.addFilter(new LogarithmicFilter())
			.addFilter(new CutoffFilter(UserInterfaceConstants.JOYSTICK_SPEED_CUTOFF))
			.build();
	
	// Attach joystick buttons here.
	public OperatorInterface() {
		
	}
	
	public boolean getDriveReverse() {
		return driveReverse.get();
	}
	
	public double getSpeed() {
		double rawSpeed = -joystick.getRawAxis(RobotInterfaceConstants.AXIS_ID_SPEED);
		return speedFilter.filter(rawSpeed);
	}
	
	public double getTurn() {
		double rawTurn = -joystick.getRawAxis(RobotInterfaceConstants.AXIS_ID_TURN);
		return turningFilter.filter(rawTurn);
	}
	
	public double getMaxSpeed() {
		double maxSpeed = -joystick.getRawAxis(RobotInterfaceConstants.AXIS_ID_MAX_SPEED);
		maxSpeed = 0.2 * (maxSpeed + 1) + 0.6;
		return maxSpeed;
	}
	
	public void periodic() {
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Shooter throttle", getMaxSpeed());
	}
}
