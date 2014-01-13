/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.cldc.jna.ptr;

/**
 * Represents a pointer to a 32-bit integer. Why this would be needed I'm not
 * quite sure. At any rate, in the simulator this mostly just wraps an int and
 * throws exceptions on occasion.
 * <p/>
 * @author wolf
 */
public class IntByReference extends ByReference {

	protected int m_value;

	public IntByReference(int value) {
		m_value = value;
	}

	public int getValue() {
		return m_value;
	}

	public void setValue(int newValue) {
		m_value = newValue;
	}

	public void free() {
		throw new UnsupportedOperationException("Calls to IntByReference cannot be used in the simulator.");
	}
}
