/*
 * Class: DistancePIDOutput
 * Author: Robert Ciborowski, Julian Dominguez-Schatz and Rana Rauf
 * Date: 15/02/2018
 * Description: A class for taking input from a distance PID Controller
 *              and providing an output to the motors.
 */

package org.usfirst.frc.team854.robot.PID;

import org.usfirst.frc.team854.robot.Robot;
import edu.wpi.first.wpilibj.PIDOutput;

public class DistancePIDOutput implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		Robot.chassisSubsystem.setMotors(output, output);
	}
}
