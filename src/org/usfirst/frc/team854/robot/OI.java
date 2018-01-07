package org.usfirst.frc.team854.robot;

import org.usfirst.frc.team854.robot.RobotMap;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team854.robot.commands.ShooterOff;
import org.usfirst.frc.team854.robot.commands.ShooterOn;

public class OI {
	private Joystick joystick = new Joystick(RobotMap.joystickPort);
	
	private  Button indexerUp = new JoystickButton(joystick, 1),
			 		indexerDown = new JoystickButton(joystick, 11),
			 		intake = new JoystickButton(joystick, 2),
			 		intakeReverse = new JoystickButton(joystick, 12),
			 		climberUp = new JoystickButton(joystick,4),
			 		climberDown = new JoystickButton(joystick,6),
			 		shooterOn = new JoystickButton(joystick,5),
			 		shooterOff = new JoystickButton(joystick,3),
			 		driveReverse = new JoystickButton(joystick,9);
	
	//Attach joystick buttons here
	public OI() {
		shooterOn.whenPressed(new ShooterOn());
		shooterOff.whenPressed(new ShooterOff());
	}
	
	public boolean getDriveReverse() {
		return driveReverse.get();
	}
	
	public boolean getShooterOn() {
		return shooterOn.get();
	}
	
	public boolean getShooterOff() {
		return shooterOff.get();
	}
	
	public boolean getClimberUp() {
		return climberUp.get();
	}
	
	public boolean getClimberDown() {
		return climberDown.get();
	}
	
	public boolean getIndexerUp() {
		return indexerUp.get();
	}
	
	public boolean getIndexerDown() {
		return indexerDown.get();
	}
	
	public boolean getIntake() {
		return intake.get();
	}
	
	public boolean getIntakeReverse() {
		return intakeReverse.get();
	}
	
	//Raw joystick info for dashboard
		public double getRawSpeed() {
			double rawSpeed = - joystick.getRawAxis(RobotMap.SpeedAxis);
			return rawSpeed;
		}
		
		public double getRawTurn() {
			double rawTurn = joystick.getRawAxis(RobotMap.TurnAxis);
			return rawTurn;
		}
		
		public double getSpeed() {
			double rawSpeed = - joystick.getRawAxis(RobotMap.SpeedAxis);
			
			//To increase center sensitivity while retaining full range,
			//we multiply the speed by itself. If an even polynomial,
			//use Math.abs() on all but one of the terms to keep the
			//direction
			double cubeSpeed = rawSpeed*rawSpeed*rawSpeed;
			
			//The joystick won't always centre properly (eg you let go of the controls
			//and it gives you a small output value instead of 0) so we check if the value
			//it's returned (except made positive to check for negative values as well) is 
			//less than some determined number, usually less than 0.03 
			if (Math.abs(cubeSpeed) < RobotMap.joystickCenterSensitivity) { return 0; }
			return cubeSpeed;
		}
		
		public double getTurn() {
			double rawTurn = joystick.getRawAxis(RobotMap.TurnAxis);
			
			//To increase center sensitivity while retaining full range,
			//we multiply the speed by itself. If an even polynomial,
			//use Math.abs() on all but one of the terms to keep the
			//direction
			double cubeTurn = rawTurn*rawTurn*rawTurn;
			
			//The joystick won't always centre properly (eg you let go of the controls
			//and it gives you a small output value instead of 0) so we check if the value
			//it's returned (except made positive to check for negative values as well) is 
			//less than some determined number, usually less than 0.03 
			if (Math.abs(cubeTurn) < RobotMap.joystickCenterSensitivity) { 
				return 0.0;
			}
			return cubeTurn;
		}
		
		public double getThrottle() {
			double throttle = -joystick.getRawAxis(RobotMap.throttleAxis);
			throttle = 0.2*(throttle + 1) + 0.6;
			return throttle;
		}
		
		public void periodic() {
		}
		
		public void updateDashboard() {
			SmartDashboard.putNumber("Shooter throttle", getThrottle());
		}
}
