package com.draco18s.harderores.entity.capability;

import com.draco18s.hardlib.api.capability.RawMechanicalPowerHandler;

public class PackagerMechanicalPowerHandler extends RawMechanicalPowerHandler {

	public PackagerMechanicalPowerHandler() {
	}
	
	@Override
	public int minimumTorque() {
		return 500;
	}

	@Override
	public float getScaledPower(int p) {
		float f = p / 60f;
		return Math.max(((float)Math.sqrt(f)-0.1f)*0.84f,0);
	}
}