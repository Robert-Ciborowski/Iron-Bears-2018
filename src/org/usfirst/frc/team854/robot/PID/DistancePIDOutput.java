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
	double targetAngle = 0;
	
	public void setTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	public void pidWrite(double output) {
		// We're currently setting the angle to zero. We will change this to not do that later.
		// System.out.println("Distance!");
		Robot.chassisSubsystem.setGyroTargetMotion(targetAngle, output);
		//Robot.chassisSubsystem.setGyroTargetMotion(0, 0);
		// System.out.println("Output: " + output);
	}
}
