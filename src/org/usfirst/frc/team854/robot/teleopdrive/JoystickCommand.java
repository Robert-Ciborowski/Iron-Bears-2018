/**
 * Name: JoystickCommand
 * Authors: Robert Ciborowski
 * Date: 20/01/2018
 * Description: Interfaces the joystick hardware input in a software environment
 */

package org.usfirst.frc.team854.robot.teleopdrive;

import org.usfirst.frc.team854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickCommand extends Command {

	public JoystickCommand() {
		requires(Robot.chassisSubsystem);
	}

	private boolean driveReverseState = false;
	private boolean reverseButtonHeld = false;

	private boolean testState = false;
	private boolean testButtonHeld = false;
	
	private boolean enabled = false;
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		System.out.println("Joystick: " + enabled);
		if (enabled) {
			double speed = Robot.oi.getSpeed();  // Positive forwards
	    	double turn = Robot.oi.getTurn(); // Positive left, sums inputs
	    	
	    	if (Robot.oi.getTestButtonPressed()) {
	    		if(!testButtonHeld) {
	    			testButtonHeld = true;
	    			if (testState) {
	    				testState = false;
	    			} else {
	    				testState = true;
	    			}
	        		Robot.intakeSubsystem.updateState(testState);
	    		}
	    	}
	    	
	    	if(Robot.oi.getDriveReverse()) {
	    		if(!reverseButtonHeld) {
	    			reverseButtonHeld = true;
	    			if (driveReverseState) {
	    				driveReverseState = false;
	    			} else {
	    				driveReverseState = true; 
	    			}
	    		}
	    	} else {
	    		reverseButtonHeld = false;
	    	}
	
	    	if (driveReverseState) {
	    		speed *= -1;
	    	}
	    	
			Robot.chassisSubsystem.setTargetMotion(turn, speed);
		}
	}

	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		end();
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}