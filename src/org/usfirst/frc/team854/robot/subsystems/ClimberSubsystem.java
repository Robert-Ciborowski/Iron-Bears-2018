/**
 * Name: ClimberSubsystem
 * Authors: Julian Dominguez-Schatz, Robert Ciborowski, Shaiza Hashmi
 * Date: 10/02/2018
 * Description: The subsystem of our robot that controls its arm.
 */

package org.usfirst.frc.team854.robot.subsystems;

import org.usfirst.frc.team854.robot.CustomSubsystem;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMode;
import org.usfirst.frc.team854.robot.constants.RobotInterfaceConstants;
import org.usfirst.frc.team854.robot.hardware.InterfaceType;

import edu.wpi.first.wpilibj.Spark;

public class ClimberSubsystem extends CustomSubsystem {
//	private Spark climbMotor = Robot.devices.getDevice(InterfaceType.PWM, RobotInterfaceConstants.PORT_MOTOR_CLIMBER);

    public ClimberSubsystem() {
//    	climbMotor.set(0);
    }

    @Override
    public void periodic() {
    	// System.out.println("Climb Motor: " + climbMotor.get());
    }

    @Override
    public void setEnabled(boolean enabled) {
    	super.setEnabled(enabled);
		System.out.println("WARNING: CLIMBER DISABLED");
    	
    	if (!enabled) {
//    		climbMotor.set(0);
    		System.out.println("CLIMBER OFF!");
    	} else {
//    		climbMotor.set(1);
    		System.out.println("CLIMBER ON!");
    	}
    }

    @Override
	public void setCurrentMode(RobotMode mode) {
    	super.setCurrentMode(mode);
    	
//    	if (enabled) {
//			switch (mode) {
//				case TELEOPERATED:
//					climbMotor.set(0);
//					break;
//				case AUTONOMOUS:
//					climbMotor.set(0);
//					break;
//				case DISABLED:
//					climbMotor.set(0);
//					break;
//				case TEST:
//					climbMotor.set(0);
//					break;
//				default:
//					climbMotor.set(0);
//					break;
//			}
//    	}
	}
    
    public void setMotor(double speed) {
    	if (enabled) {
//    		climbMotor.set(speed);
    	}
    }

	@Override
	public void updateDashboard() {
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

