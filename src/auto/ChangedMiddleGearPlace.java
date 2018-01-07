package auto;

import org.usfirst.frc.team854.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team854.robot.commands.IndexerOn;
import org.usfirst.frc.team854.robot.commands.IntakeInOut;
import org.usfirst.frc.team854.robot.commands.ShooterAuto;

public class ChangedMiddleGearPlace extends CommandGroup {
    
	private double firstDistance = 37.11;//31.35;
	private double secondDistance = 34.73;//30.65-5; //REMOVED -5 BECAUSE THERE'S NOW A TIMEOUT
	private double thirdDistance = -140;
	
    public  ChangedMiddleGearPlace(boolean leftRight) { //if 0 turn left, if 1 turn right
    	/*int angle1 = 0;
    	if(leftRight) {angle1 = 135;}
    	else {angle1 = -135;}
    	
    	int angle2 = 0;
    	if(leftRight) {angle2 = 180;}
    	else {angle2 = -180;}
    	
    	int angle3 = 0;
    	if(leftRight) {angle3 = 120;}
    	else {angle3 = -120;}*/
    	int angle1 = 0;
    	int angle2 = 0;
    	int angle3 = 0;
    	
    	Robot.chassisSubsystem.resetGyroHeading();
    	
    	addSequential(new DriveToDistance(0.3, 0, -(103+16)));
    	addSequential(new RotateToAngle(-60,2));
    	addSequential(new DriveToDistance(0.3,-60,0));
    	//addSequential(new RotateToAngle(angle1,2));
    	//addSequential(new DriveToDistanceTimeOut(0.3,angle1,secondDistance,2)); //changed to timeout
    	//addSequential(new ShootWaitCommand(0.5));
    	//addParallel(new ShooterAuto(8.5));
    	//addSequential(new IndexerOn(0));
    	//addSequential(new IntakeInOut());
    	//addSequential(new WaitCommand(3));
    	//addSequential(new IndexerOn(3,0.2)); //INDEXER AT A SLOWER SPEED TO STOP KNOCKING BALLS!!!
    	/*addSequential(new DriveToDistance(0.5,angle1,-30));
    	addSequential(new RotateToAngle(angle2,2));
    	addSequential(new DriveToDistance(0.5,angle2,thirdDistance));*/
    	/*addSequential(new DriveToDistance(0.4,angle1,-24));
    	addSequential(new RotateToAngle(angle2,2));
    	addSequential(new DriveToDistance(0.4,angle2,45.76));
    	addSequential(new RotateToAngle(angle3,2));
    	addSequential(new DriveToDistance(0.4,angle3,79.61));*/
    }
}
