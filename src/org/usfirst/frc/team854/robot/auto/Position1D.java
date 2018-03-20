/*
 * Name: Position1D
 * Author: Julian Dominguez-Schatz
 * Date: 17/02/2018
 * Description: Represents a particular position along a 1D axis.
 */

package org.usfirst.frc.team854.robot.auto;

public enum Position1D {
	LEFT('L'), RIGHT('R'), NEUTRAL(' ');

	private final char key;

	private Position1D(char key) {
		this.key = key;
	}

	public static Position1D fromChar(char key) {
		for (Position1D item : values()) {
			if (item.key == key) {
				return item;
			}
		}
		return NEUTRAL;
	}
}
