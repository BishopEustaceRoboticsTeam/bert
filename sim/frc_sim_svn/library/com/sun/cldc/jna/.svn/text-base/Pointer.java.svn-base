/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.cldc.jna;

import com.sun.squawk.Address;
import com.sun.squawk.realtime.*;
import net.sourceforge.frcsimulator.mistware.Simulator;

/**
 * Replacement for Pointer class, which probably shouldn't be used in the
 * simulator.
 * <p/>
 * @todo implement or determine if should be allowed at all
 * @author wolf
 */
public class Pointer extends RawMemoryFloatAccess {

	public Pointer(int size) {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
		//@TODO eventually do away with pointer entirely, as of now it is quite engrained into the wpi libraries
		//throw new UnsupportedOperationException("Not implemented yet; should be replaced.");
	}

	public Pointer(long base, int size) {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
		//throw new UnsupportedOperationException("Not implemented yet; should be replaced.");
	}

	public static Pointer NULL() {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
		return new Pointer(0); //@TODO, should not be used, but currently required
	}

	public final Address address() {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
		return new Address(); //@TODO should not be used
	}

	public final int getSize() {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
		return 0; //@TODO should not be used
	}

	public final void free() throws IllegalStateException {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
	}

	public static void copyBytes(Pointer src, int srcOffset, Pointer dst, int dstOffset, int len)
			throws OffsetOutOfBoundsException, SizeOutOfBoundsException {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
	}

	public static Pointer createStringBuffer(String value) throws OutOfMemoryError {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
		return new Pointer(0);
	}

	public final void setString(int offset, String value) {
		Simulator.fixme(Pointer.class, Thread.currentThread(), "should not be used");
	}
}
