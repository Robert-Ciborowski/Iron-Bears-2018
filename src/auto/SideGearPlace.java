package auto;

import org.usfirst.frc.team854.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideGearPlace extends CommandGroup {
    
	public enum Side {
		LEFT, RIGHT
	};
	
	private static final double DISTANCE_FWD = 105.4708;
	private static final double ANGLE = 150;
	private static final double DISTANCE_SIDEWAYS = 8.5051;
	
    public  SideGearPlace(Side side) {
    	Robot.chassisSubsystem.resetGyroHeading();
    	System.out.println("Gyro heading reset");
    	addSequential(new DriveToDistance(0.3, 0, -DISTANCE_FWD));
    	if (side == Side.RIGHT) {
    		addSequential(new RotateToAngle(150,2));
    	} else {
    		addSequential(new RotateToAngle(-30,2));
    	}
    	addSequential(new DriveToDistance(0.3,-60, -DISTANCE_SIDEWAYS));
    }
}
