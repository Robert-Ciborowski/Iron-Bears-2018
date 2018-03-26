/*
 * Name: AutoConfig
 * Author: Julian Dominguez-Schatz
 * Date: 20/03/2018
 * Description: Represents configuration attributes for an auto run.
 */

package org.usfirst.frc.team854.robot.auto;

import java.util.HashMap;

public class AutoConfig {
	private final FieldTarget target;
	private final Position1D startingLocation;
	private final HashMap<FieldTarget, Position1D> fieldLayout;
	private final boolean overextend;
	private final boolean twoCube;

	private AutoConfig(Builder builder) {
		this.target = builder.fieldTarget;
		this.startingLocation = builder.startingLocation;
		this.fieldLayout = builder.fieldLayout;
		this.overextend = builder.overextend;
		this.twoCube = builder.twoCube;
	}

	public FieldTarget getFieldTarget() {
		return target;
	}

	public Position1D getPositionForTarget(FieldTarget target) {
		return fieldLayout.get(target);
	}

	public boolean shouldOverextend() {
		return overextend;
	}

	public boolean shouldRunTwoCubeAuto() {
		return twoCube;
	}
	
	public Position1D getStartingLocation() {
		return startingLocation;
	}

	public static class Builder {
		private FieldTarget fieldTarget;
		private Position1D startingLocation;
		private HashMap<FieldTarget, Position1D> fieldLayout;
		private boolean overextend;
		private boolean twoCube;

		public Builder(String fieldStateString) {
			fieldTarget = FieldTarget.NONE;
			startingLocation = Position1D.NEUTRAL;
			
			if (fieldStateString.length() < 3) {
				throw new IllegalStateException("You aren't in auto.");
			}
			
			fieldLayout = new HashMap<>();

			fieldLayout.put(FieldTarget.LOCAL_SWITCH, Position1D.fromChar(fieldStateString.charAt(0)));
			fieldLayout.put(FieldTarget.SCALE, Position1D.fromChar(fieldStateString.charAt(1)));
			fieldLayout.put(FieldTarget.FOREIGN_SWITCH, Position1D.fromChar(fieldStateString.charAt(2)));
			
			overextend = true;
			twoCube = false;
		}
		
		public Builder fieldTarget(FieldTarget fieldTarget) {
			this.fieldTarget = fieldTarget;
			return this;
		}
		
		public Builder startingLocation(Position1D startingLocation) {
			this.startingLocation = startingLocation;
			return this;
		}
		
		public Builder overextend(boolean overextend) {
			this.overextend = overextend;
			return this;
		}
		
		public Builder twoCube(boolean twoCube) {
			this.twoCube = twoCube;
			return this;
		}
		
		public AutoConfig build() {
			return new AutoConfig(this);
		}
		
	}
}
