/*
 * Class: AutoSwitch
 * Author: Robert Ciborowski and Waseef Nayeem
 * Date: Early 2017
 * Description: A class that interprets the input from the autonomous mode selection switches and
 *              gives an output accordingly.
 */

package auto;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoSwitch {
	// There are two switches, with are binary. Together, they can select up to 4 different options.
	private DigitalInput switch0, switch1;
	private byte state;
	
	public AutoSwitch(int channel0, int channel1) {
		switch0 = new DigitalInput(channel0);
		switch1 = new DigitalInput(channel1);
	}
	
	public void update() {
		state = 0;
		
		if (switch0.get()) {
			state += 1;
		}
		
		if (switch1.get()) {
			state += 2;
		}
	}
	
	public byte getState() {
		return state;
	}
}