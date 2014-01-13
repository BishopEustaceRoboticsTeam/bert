/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.communication;

import com.sun.cldc.jna.Pointer;
import net.sourceforge.frcsimulator.internals.UnsimulatedOperationException;

/**
 *
 * @author wolf
 */
abstract class InternalSemaphore {

	public final Pointer getPointer() {
		throw new UnsimulatedOperationException("Don't use pointers in the simulator (in Semaphore)");
	}

	/**
	 * Unblock every task that is blocked by the semaphore.
	 * <p/>
	 * @throws SemaphoreException
	 */
	public abstract void flush() throws SemaphoreException;

	/**
	 * Release the semaphore.
	 * <p/>
	 * @throws SemaphoreException
	 */
	public abstract void give() throws SemaphoreException;

	/**
	 * Take the semaphore. Block for timeout milliseconds.
	 * <p/>
	 * @param timeout The number of milliseconds to block.
	 * <p/>
	 * @throws SemaphoreException if the lock can't be take in timeout seconds
	 */
	public final void takeMillis(int timeout) throws SemaphoreException {
		boolean taken = false;
		long time = System.currentTimeMillis();
		while(!taken && (timeout == Semaphore.WAIT_FOREVER || time + timeout > System.currentTimeMillis())) {
			try {
				if(timeout == Semaphore.WAIT_FOREVER) {
					doWaitForever();
				} else {
					doWaitMillis(timeout);
				}
				taken = true;
			} catch(InterruptedException ex) {
				// Do nothing. (The loop will continue.)
			}
		}
		if(!taken) {
			// TODO is this the correct error code?
			throw new SemaphoreException(SemaphoreException.S_objLib_OBJ_TIMEOUT);
		}
	}

	/**
	 * Attempt to wait forever on the semaphore. Called in a loop by
	 * takeMillis() to catch InterruptedExceptions.
	 * <p/>
	 * @throws SemaphoreException
	 * @throws InterruptedException
	 */
	protected abstract void doWaitForever() throws SemaphoreException, InterruptedException;

	/**
	 * Attempt to wait for a certain number of milliseconds on a semaphore.
	 * Called in a loop by takeMillis() to catch InterruptedExceptions.
	 * <p/>
	 * @param timeout The number of milliseconds to wait, at most.
	 * <p/>
	 * @throws SemaphoreException
	 * @throws InterruptedException
	 */
	protected abstract void doWaitMillis(int timeout) throws SemaphoreException, InterruptedException;

	/**
	 * Take the semaphore. Block forever until semaphore is available.
	 * <p/>
	 * @throws SemaphoreException
	 */
	public final void takeForever() throws SemaphoreException {
		takeMillis(Semaphore.WAIT_FOREVER);
	}

	/**
	 * Attempt to take the semaphore without blocking.
	 * <p/>
	 * @return True if the semaphore was taken, false otherwise.
	 * <p/>
	 * @throws SemaphoreException
	 */
	public abstract boolean tryTake() throws SemaphoreException;

	public final void close() throws SemaphoreException {
		// TODO how on earth do you close a semaphore?
	}

	/**
	 * Free all of the resources associated with this semaphore. Actually does
	 * nothing; this is for VxWorks semaphores.
	 * <p/>
	 * @throws SemaphoreException
	 */
	public final void free() throws SemaphoreException {
		// TODO And how are you supposed to free one?
	}
}
