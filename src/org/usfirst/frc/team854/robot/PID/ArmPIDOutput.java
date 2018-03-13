/*
 * Class: DistancePIDOutput
 * Author: Robert Ciborowski, Julian Dominguez-Schatz and Rana Rauf
 * Date: 15/02/2018
 * Description: A class for taking input from a distance PID Controller
 *              and providing an output to the motors.
 */

package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;

public class ArmPIDOutput implements PIDOutput {
	private Spark armMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_ARM);
	
	@Override
	public void pidWrite(double output) {
		armMotor.set(output);
	}
}
