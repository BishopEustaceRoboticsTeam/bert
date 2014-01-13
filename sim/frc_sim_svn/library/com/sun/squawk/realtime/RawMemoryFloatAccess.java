/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.squawk.realtime;

/**
 *
 * @author wolf
 */
public class RawMemoryFloatAccess extends RawMemoryAccess {

	public double getDouble(long offset) {
		throw new UnsupportedOperationException("RawMemoryFloatAccess should not be called in the simulator!");
	}

	public void setDouble(long offset, double value) {
		throw new UnsupportedOperationException("RawMemoryFloatAccess should not be called in the simulator!");
	}

	public float getFloat(long offset) {
		throw new UnsupportedOperationException("RawMemoryFloatAccess should not be called in the simulator!");
	}

	public void setFloat(long offset, float value) {
		throw new UnsupportedOperationException("RawMemoryFloatAccess should not be called in the simulator!");
	}

	public void getFloats(long offset, float[] floats, int low, int number) {
		throw new UnsupportedOperationException("RawMemoryFloatAccess should not be called in the simulator!");
	}

	public void setFloats(long offset, float[] floats, int low, int number) {
		throw new UnsupportedOperationException("RawMemoryFloatAccess should not be called in the simulator!");
	}
}
