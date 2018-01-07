package teleopdrive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team854.robot.Robot;
import org.usfirst.frc.team854.robot.RobotMap;

public class JoystickCommand extends Command{
	
	public JoystickCommand() {
		requires(Robot.chassisSubsystem);
		requires(Robot.climberSubsystem);
		requires(Robot.indexerSubsystem);
		requires(Robot.intakeSubsystem);
	}
	
	boolean driveReverseState = false;
	boolean reverseButtonHeld =false;
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		
		//Data is already cubed (for center sensitivity) in OI method
		double speed = Robot.oi.getSpeed();  //Positive forwards
    	double turn = Robot.oi.getTurn(); //Positive right, sums inputs
    	
    	if(Robot.oi.getDriveReverse()) {
    		if(!reverseButtonHeld) {
    			reverseButtonHeld = true;
    			if(driveReverseState) {
    				driveReverseState = false;
    			}
    			else {
    				driveReverseState = true;
    			}
    		}
    	}
    	else {
    		reverseButtonHeld = false;
    		}
    	
    	if(driveReverseState) {
    		speed *= -1;
    	}
    	
    	double leftSpeed = 0;
    	double rightSpeed = 0;
    	
    	if (speed > 0.0) {
		      if (turn < 0.0) {
		        rightSpeed = speed + turn;
		        leftSpeed = Math.max(speed, -turn);
		      } else {
		        rightSpeed = Math.max(speed, turn);
		        leftSpeed = speed - turn;
		      }
		    } else {
		      if (turn < 0.0) {
		        rightSpeed = -Math.max(-speed, -turn);
		        leftSpeed = speed - turn;
		      } else {
		        rightSpeed = speed + turn;
		        leftSpeed = -Math.max(-speed, turn);
		      }
		    }
    		Robot.chassisSubsystem.setMotorSpeed(leftSpeed, rightSpeed);
    		
    		//INDEXER
    		if(Robot.oi.getIndexerUp()) {
    			Robot.indexerSubsystem.setIndexerSpeed(RobotMap.indexerSpeed);
    		}
    		else if(Robot.oi.getIndexerDown()) {
    			Robot.indexerSubsystem.setIndexerSpeed(-RobotMap.indexerSpeed);
    		}
    		else {
    			Robot.indexerSubsystem.indexerOff();
    		}
    		
    		//INTAKE
    		if(Robot.oi.getIntake()) {
    			Robot.intakeSubsystem.setIntakeSpeed(RobotMap.intakeSpeed);
    		}
    		else if(Robot.oi.getIntakeReverse()) {
    			Robot.intakeSubsystem.setIntakeSpeed(-RobotMap.intakeSpeed);
    		}
    		else {
    			Robot.intakeSubsystem.intakeOff();
    		}
    		
    		//CLIMBER
    		if(Robot.oi.getClimberUp()) {
    			Robot.climberSubsystem.setClimberSpeed(RobotMap.climberSpeed);
    		}
    		else { Robot.climberSubsystem.climberOff();}
    		
    		
	}
    		
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		end();
	}
}