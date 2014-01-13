/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.communication;

import java.util.concurrent.TimeUnit;

/**
 * Emulates various VXWorks semaphores using Java native methods. Note: The
 * options passed in currently are completely ignored.
 * <p/>
 * @todo do something with the options?
 * @author wolf
 */
public class Semaphore extends InternalSemaphore {

	public static final int WAIT_FOREVER = -1;
	static final int SEM_Q_FIFO = 0x00; /*
	 * first in first out queue
	 */

	static final int SEM_Q_PRIORITY = 0x01; /*
	 * priority sorted queue
	 */

	static final int SEM_DELETE_SAFE = 0x04; /*
	 * owner delete safe (mutex opt.)
	 */

	static final int SEM_INVERSION_SAFE = 0x08; /*
	 * no priority inversion (mutex opt.)
	 */

	static final int SEM_EVENTSEND_ERR_NOTIFY = 0x10; /*
	 * notify when eventRsrcSend fails
	 */

	static final int SEM_INTERRUPTIBLE = 0x20; /*
	 * interruptible on RTP signal
	 */

	private Options options;
	private InternalSemaphore m_semaphore;

	/**
	 * Create a new mutex semaphore.
	 * <p/>
	 * @param options The options to create the semaphore with.
	 */
	public Semaphore(Options options) {
		// TODO what to do with options?
		m_semaphore = new MutexSemaphore();
	}

	/**
	 * Create a boolean semaphore with the given initial state.
	 * <p/>
	 * @param options The options to create the semaphore with.
	 * @param initialState The initial state for the semaphore to have.
	 */
	public Semaphore(Options options, boolean initialState) {
		m_semaphore = new CountingSemaphore(1);
		if(initialState) {
			try {
				m_semaphore.tryTake();
			} catch(SemaphoreException ex) {
				// Do nothing (an exception is never thrown)
			}
		}
	}

	/**
	 * Create a counting semaphore with the given number of permits.
	 * <p/>
	 * @param options The options to create the semaphore with.
	 * @param count The initial count for the semaphore to hold.
	 */
	public Semaphore(Options options, int count) {
		m_semaphore = new CountingSemaphore(count);
	}

	/**
	 * Unblock every task that is blocked by the semaphore.
	 * <p/>
	 * @throws SemaphoreException
	 */
	@Override
	public void flush() throws SemaphoreException {
		m_semaphore.flush();
	}

	/**
	 * Release the semaphore.
	 * <p/>
	 * @throws SemaphoreException
	 */
	@Override
	public void give() throws SemaphoreException {
		m_semaphore.give();
	}

	/**
	 * Attempt to take the semaphore, but don't block.
	 * <p/>
	 * @return True if the semaphore was successfully taken, false otherwise
	 * <p/>
	 * @throws SemaphoreException
	 */
	@Override
	public boolean tryTake() throws SemaphoreException {
		return m_semaphore.tryTake();
	}

	@Override
	protected void doWaitForever() throws SemaphoreException, InterruptedException {
		m_semaphore.doWaitForever();
	}

	@Override
	protected void doWaitMillis(int timeout) throws SemaphoreException, InterruptedException {
		m_semaphore.doWaitMillis(timeout);
	}

	/**
	 * A mutex semaphore implementation using only Object.wait() &
	 * Object.notify().
	 */
	private class MutexSemaphore extends InternalSemaphore {

		private final Object semaphore = new Object();

		@Override
		public void flush() throws SemaphoreException {
			synchronized(semaphore) {
				semaphore.notifyAll();
			}
		}

		@Override
		public void give() throws SemaphoreException {
			synchronized(semaphore) {
				semaphore.notify();
			}
		}

		@Override
		public boolean tryTake() throws SemaphoreException {
			return Thread.holdsLock(semaphore);
		}

		@Override
		protected void doWaitForever() throws SemaphoreException, InterruptedException {
			synchronized(semaphore) {
				semaphore.wait();
			}
		}

		@Override
		protected void doWaitMillis(int timeout) throws SemaphoreException, InterruptedException {
			synchronized(semaphore) {
				semaphore.wait(timeout);
			}
		}
	}

	/**
	 * A counting semaphore implementation using Java native semaphores.
	 */
	private class CountingSemaphore extends InternalSemaphore {

		java.util.concurrent.Semaphore semaphore;

		public CountingSemaphore(int permits) {
			semaphore = new java.util.concurrent.Semaphore(permits);
		}

		@Override
		public synchronized void flush() throws SemaphoreException {
			// TODO How do you flush a semaphore? Put it in the toilet?
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public synchronized void give() throws SemaphoreException {
			semaphore.release();
		}

		@Override
		public synchronized boolean tryTake() throws SemaphoreException {
			return semaphore.tryAcquire();
		}

		@Override
		protected void doWaitForever() throws SemaphoreException, InterruptedException {
			semaphore.acquireUninterruptibly();
		}

		@Override
		protected void doWaitMillis(int timeout) throws SemaphoreException, InterruptedException {
			if(!semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS)) { // Acquire failed
				// TODO is this the correct error code?
				throw new SemaphoreException(SemaphoreException.S_objLib_OBJ_TIMEOUT);
			}
		}
	}

	/**
	 * Options to create a semaphore with.
	 */
	public static class Options {

		int value = 0;

		/**
		 * Set true to use a priority sorted queue, false to use first-in
		 * first-out
		 * <p/>
		 * @param priority
		 */
		public void setPrioritySorted(boolean priority) {
			if(priority) {
				value |= SEM_Q_PRIORITY;
			} else {
				value &= ~SEM_Q_PRIORITY;
			}
		}

		/**
		 * Set whether or not the semaphore is delete safe.
		 * <p/>
		 * @param delSafe True to make the semaphore delete safe.
		 */
		public void setDeleteSafe(boolean delSafe) {
			if(delSafe) {
				value |= SEM_DELETE_SAFE;
			} else {
				value &= ~SEM_DELETE_SAFE;
			}
		}

		/**
		 * Set whether the semaphore is inversion safe.
		 * <p/>
		 * @param invSafe True to set the semaphore to inversion safe.
		 */
		public void setInversionSafe(boolean invSafe) {
			if(invSafe) {
				value |= SEM_INVERSION_SAFE;
			} else {
				value &= ~SEM_INVERSION_SAFE;
			}
		}

		/**
		 * Set whether the semaphore should notify on an error.
		 * <p/>
		 * @param errNot True to set error notify.
		 */
		public void setErrorNotify(boolean errNot) {
			if(errNot) {
				value |= SEM_EVENTSEND_ERR_NOTIFY;
			} else {
				value &= ~SEM_EVENTSEND_ERR_NOTIFY;
			}
		}

		/**
		 * Set whether the semaphore is interruptable.
		 * <p/>
		 * @param intable True allows this semaphore to be interrupted.
		 */
		public void setInterruptable(boolean intable) {
			if(intable) {
				value |= SEM_INTERRUPTIBLE;
			} else {
				value &= ~SEM_INTERRUPTIBLE;
			}
		}
	}
}
