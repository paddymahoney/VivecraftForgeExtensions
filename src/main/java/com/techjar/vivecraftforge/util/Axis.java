
package com.techjar.vivecraftforge.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author Techjar
 */
@SideOnly(Side.CLIENT)
public enum Axis {
	PITCH(-1, 0, 0),
	YAW(0, -1, 0),
	ROLL(0, 0, -1),
	UNKNOWN(0, 0, 0);

	private Vector3 vector;

	private Axis(float x, float y, float z) {
		this.vector = new Vector3(x, y, z);
	}

	public Vector3 getVector() {
		return vector.copy();
	}
}
