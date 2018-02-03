/**
 * Name: OperatorInterface
 * Authors: Robert Ciborowski, Waseef Nayeem, Julian Dominguez-Schatz
 * Date: 06/01/2018
 * Description: Describes the user interface of the operator of the robot, including
 *              filters for the inputs.
 */

package oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.UserInterfaceConstants;

public class OperatorInterface {
	private Joystick joystick = new Joystick(UserInterfaceConstants.PORT_JOYSTICK);
	private Button driveReverse = new JoystickButton(joystick, 9);

	private Filter turningFilter = new CompoundFilter.Builder()
			.addFilter(new LinearFilter(UserInterfaceConstants.JOYSTICK_TURNING_OFFSET))
			.addFilter(new LogarithmicFilter(Math.PI))
			.addFilter(new CutoffFilter(UserInterfaceConstants.JOYSTICK_TURNING_CUTOFF))
			.build();
	private Filter speedFilter = new CompoundFilter.Builder()
			.addFilter(new LinearFilter(UserInterfaceConstants.JOYSTICK_SPEED_OFFSET))
			.addFilter(new LogarithmicFilter())
			.addFilter(new CutoffFilter(UserInterfaceConstants.JOYSTICK_SPEED_CUTOFF))
			.build();
	
	public OperatorInterface() {
		
	}
	
	/** Returns the drive reverse button.*/
	public boolean getDriveReverse() {
		return driveReverse.get();
	}
	
	/** Returns the filtered speed that is inputed by the user.*/
	public double getSpeed() {
		double rawSpeed = -joystick.getRawAxis(UserInterfaceConstants.AXIS_ID_SPEED);
		return speedFilter.filter(rawSpeed);
	}
	
	/** Returns the filtered turn that is inputed by the user.*/
	public double getTurn() {
		double rawTurn = -joystick.getRawAxis(UserInterfaceConstants.AXIS_ID_TURN);
		return turningFilter.filter(rawTurn);
	}
	
	/** Returns the max speed that is inputed by the user.*/
	public double getMaxSpeed() {
		double maxSpeed = -joystick.getRawAxis(UserInterfaceConstants.AXIS_ID_MAX_SPEED);
		maxSpeed = 0.2 * (maxSpeed + 1) + 0.6;
		return maxSpeed;
	}
	
	public void periodic() {
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Max speed", getMaxSpeed());
		SmartDashboard.putNumber("Joystick speed value", getSpeed());
		SmartDashboard.putNumber("Joystick turn value", getTurn());
	}
}
