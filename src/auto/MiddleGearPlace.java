package auto;

import org.usfirst.frc.team854.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleGearPlace extends CommandGroup {
    
	private static final double DISTANCE_TO_PEG = 79 + 5 - 4.5;
	
    public  MiddleGearPlace() {
    	Robot.chassisSubsystem.resetGyroHeading();
    	System.out.println("Gyro heading reset");
    	addSequential(new DriveToDistance(0.325, 0, -DISTANCE_TO_PEG));
    }
}
