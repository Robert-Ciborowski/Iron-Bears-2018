package auto;

import org.usfirst.frc.team854.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team854.robot.commands.IndexerOn;
import org.usfirst.frc.team854.robot.commands.ShooterAuto;

public class HopperAutoShot extends CommandGroup {
    
	private double firstDistance = 74.25 + 12+10-10;
	private double secondDistance = 37.11;//DON'T ADD EXTRA INCHES TO TOUCH THE PLATE HERE!!! IT AFFECTS THE REST
	private double thirdDistance = 37.14 -4-10-6;
	private double fourthDistance = 34.73 + 4 + 3+6; //ADDED 4 INCHES TO MAKE SURE IT TOUCHES
	
    public  HopperAutoShot(boolean leftRight) { //if 0 turn left, if 1 turn right
    	int angle1 = 0;
    	if(leftRight) {angle1 = 90;}
    	else {angle1 = -90;}
    	
    	int angle2 = 0;
    	if(leftRight) {angle2 = 180;}
    	else {angle2 = -180;}
    	
    	int angle3 = 0;
    	if(leftRight) {angle3 = 135;}
    	else {angle3 = -135;}
    	
    	Robot.chassisSubsystem.resetGyroHeading();
    	//9 feet from wall to front edge of hopper trigger panel
    	
    	addSequential(new DriveToDistance(0.6,0,firstDistance/4));
    	addSequential(new DriveToDistance(0.4,0,(3*firstDistance)/4));
    	addSequential(new RotateToAngle(angle1,2));
    	addSequential(new DriveToDistanceTimeOutNoDistance(0.4,angle1,secondDistance + 4,2.5)); //ADD 4 inches here to touch!
    	addSequential(new DriveToDistance(0.4,angle1,-secondDistance));
    	addSequential(new RotateToAngle(angle2,2));
    	addSequential(new DriveToDistance(0.6,angle2,thirdDistance/4));
    	addSequential(new DriveToDistance(0.4,angle2,(3*thirdDistance)/4));
    	addSequential(new RotateToAngle(angle3,2));
    	addSequential(new DriveToDistance(0.6,angle3,fourthDistance/4));
    	addSequential(new DriveToDistanceTimeOut(0.4,angle3,(3*fourthDistance)/4,2));
    	addParallel(new ShootWaitCommand(0.5)); //MOVED THIS FROM BEFORE SHOOTER AUTO CALLED BELOW, AS A SEQUENTIAL
    	addSequential(new DriveToDistanceTimeOut(0.4,angle3,8,0.5));
    	
    	//addParallel(new ShooterAuto(15));
    	//addSequential(new IndexerOn(15,1.0));
    }
}
