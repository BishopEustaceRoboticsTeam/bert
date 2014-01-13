/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.squawk.realtime;

import net.sourceforge.frcsimulator.internals.CRIO;
import net.sourceforge.frcsimulator.mistware.Simulator;

/**
 *
 * @author wolf
 */
class RawMemoryAccess {

	public byte getByte(long offset) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
		return 0;
	}

	public void getBytes(long offset, byte[] bytes, int low, int number) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
	}

	public void setByte(long offset, byte value) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
	}

	public void setBytes(long offset, byte[] bytes, int low, int number) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
	}

	public int getInt(long offset) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
		return 0;
	}

	public void setInt(long offset, int value) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
	}

	public short getShort(long offset) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
		return 0;
	}

	public void getShorts(long offset, short[] shorts, int low, int number) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
	}

	public void setShort(long offset, short value) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
	}

	public void setShorts(long offset, short[] shorts, int low, int number) {
		Simulator.fixme(RawMemoryAccess.class, Thread.currentThread(), "should not be used");
	}
}
