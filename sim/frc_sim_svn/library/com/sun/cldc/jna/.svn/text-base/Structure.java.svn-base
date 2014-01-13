/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.cldc.jna;

/**
 *
 * @author wolf
 */
public abstract class Structure {

	/**
	 * Note: Will throw null pointer exceptions if use attempted in simulator
	 */
	protected Pointer backingNativeMemory;

	public final void useMemory(Pointer m) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	/**
	 * @todo What should this do, if anything, in the simulator (if it should
	 * even be allowed)?
	 * @throws OutOfMemoryError
	 */
	public void allocateMemory() throws OutOfMemoryError {
		//throw new UnImplementedOperationException("Not implemented yet.");
	}

	public void freeMemory() throws IllegalStateException {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public void release() throws IllegalStateException {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public abstract void read();

	public abstract void write();

	public final Pointer getPointer() {
		return null; //@TODO shouldn't exist, but because of other issues, is needed
	}
}
