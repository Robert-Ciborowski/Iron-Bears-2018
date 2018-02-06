package org.usfirst.frc.team854.robot.constants;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

 public final class SensorsAndMotors implements RobotInterfaceConstants {
	
	
	public static Spark leftMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_LEFT);
	public static Spark rightMotor = new Spark(RobotInterfaceConstants.PORT_MOTOR_RIGHT);
    public static Spark leftMiniCIM = new Spark(RobotInterfaceConstants.PORT_MINICIM_LEFT);
    public static Spark rightMiniCIM = new Spark(RobotInterfaceConstants.PORT_MINICIM_RIGHT);
    
    
	
    public static AnalogGyro Gyro=new AnalogGyro(RobotInterfaceConstants.PORT_GYRO); 
    
    public static Encoder leftEncoder= new Encoder(PORT_ENCODER_LEFT_1,PORT_ENCODER_LEFT_2);
    
    public static Encoder rightEncoder =new Encoder (PORT_ENCODER_RIGHT_1,PORT_ENCODER_RIGHT_2);
	

}
