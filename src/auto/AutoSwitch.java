/*
 * Class: AutoSwitch
 * Author: Waseef Nayeem and Robert Ciborowski
 * Date: 2017
 * Description: A class that interprets the input from the autonomous mode selection switch and gives an output accordingly.
 */

package auto;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutoSwitch {

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