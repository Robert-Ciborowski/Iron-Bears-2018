package auto;

import org.usfirst.frc.team854.robot.Robot;

import PID.M_PIDController;
import PID.M_PIDInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoStraightPID {

	/*
	 * Angle PID Controller
	 * 
	 * The angle PID controller is declared as static so that they can be
	 * adjusted in the SmartDashboard
	 */
	private static M_PIDInput anglePIDInput = new M_PIDInput() {
		@Override
		public double pidGet() {
			return -Robot.chassisSubsystem.getAngleDifference(angleSetpoint) / 180.0;
		}
	};

	private static PIDOutput anglePIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			pidOutputValue = output;
		}
	};

	private static double angleSetpoint = 0.0;
	private static double pidOutputValue = 0.0;

	// TODO Verify this values are correct
	private static M_PIDController anglePIDController = new M_PIDController(-15.0, 0.0, 0.0, 1.0, anglePIDInput,
			anglePIDOutput);

	public static void setEnabled(boolean enabled) {
		if (enabled) {
			anglePIDController.enable();
		} else {
			anglePIDController.reset();
		}
	}

	public static boolean isEnabled() {
		return anglePIDController.isEnabled();
	}

	public static double getOutput() {
		return pidOutputValue;
	}

	public static void setSetpoint(double setpoint) {
		angleSetpoint = setpoint;
	}

	public static void periodic() {
		anglePIDController.calculate();
	}

	public static void updateDashboard() {
		SmartDashboard.putData("AnglePID", anglePIDController);
	}

}