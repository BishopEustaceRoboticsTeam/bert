/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.mistware;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.midlet.MIDlet;
import net.sourceforge.frcsimulator.internals.CRIO;

/**
 * The actual simulator, which runs the robot software. Handles initializing the
 * given MIDlet, catching various exceptions, and other tasks. NB: It's mistware
 * because it's even softer than software, but isn't in the cloud.
 * <p/>
 * @author wolf
 */
public class Simulator extends Thread {

	private MIDlet midlet;
	private static Simulator instance;
	// Anonymous logger so will work in applet
	private static Logger log = Logger.getAnonymousLogger();//Logger.getLogger("Simulator");
	private String midletName;
	private Status status = Status.READY;
	/**
	 * The throwable (if any) which flew out of the simulator and caused the
	 * simulator to be halted. This will be null unless status==Status.ERROR
	 */
	private SimulatorError error;
	/**
	 * The method which is invoked when the state of the simulator changes.
	 */
	private Method onStateChange;
	private Method onError;

	/**
	 *
	 * @param midletClass The name of the MIDlet class which should be run
	 * inside the simulator; eg. "edu.wpi.first.wpilibj.SimpleRobot
	 * <p/>
	 * @throws ClassNotFoundException if the class name given does not actually
	 * exist
	 * @throws ClassCastException if the class exists, but doesn't extend
	 * MIDlet.
	 */
	public Simulator(String midletClass) throws ClassNotFoundException, ClassCastException {
		/*
		 * Make sure that the class given actually exists. if it doesn't,
		 * Class.forName() will throw a ClassNotFoundException which will pop
		 * the stack out of this constructor. This is the long version of this
		 * call so we can pass false to prevent initializing the class.
		 */
		Class.forName(midletClass, false, this.getClass().getClassLoader()).asSubclass(MIDlet.class);
		midletName = midletClass;
		instance = this;
	}

	public static Simulator getInstance() {
		return instance;
	}

	@Override
	public void run() {
		try {
			try {
				try {
					try {
						setStatus(Status.INITIALIZING);
						midlet = ClassLoader.getSystemClassLoader().loadClass(midletName).asSubclass(MIDlet.class).newInstance();
						setStatus(Status.RUNNING);
						midlet.runMIDlet();
						setStatus(Status.STOPPED);
					} catch(UnsupportedOperationException uoe) {
						triggerError("An unsupported operation was attempted inside the simulated robot. (Many operations have not yet been implemented.)", uoe);
					}
				} catch(Exception e) {
					triggerError("An Exception occurred inside the simulated robot code.", e);
				}
			} catch(Error e) {
				triggerError("An Error occurred inside the simulated robot code.", e);
			}
		} catch(Throwable t) {
			triggerError("Odd. A generic Throwable which was not an Exception flew out of the simulated code.", t);
		}
	}

	/**
	 * Called when an error occurs inside the simulator, to notify the user that
	 * an error occurred and clean up.
	 * <p/>
	 * @param s An additional message to display before printing the stack
	 * trace.
	 * @param t The throwable which flew out of the simulator.
	 */
	private void triggerError(String s, Throwable t) {
		error = new SimulatorError(s, t);
		log.log(Level.SEVERE, s, t);
		setStatus(Status.ERROR);
		callErrorMethod(onError, error);
	}

	public static void callErrorMethod(Method handler, SimulatorError se) {
		if(handler == null) {
			displayError(se);
		} else {
			try {
				handler.invoke(null, se);
			} catch(Exception e) {
				System.err.println("WARN: The simulator error handler threw an exception:");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Prints a short status method and a stack trace for the given
	 * SimulatorError.
	 * <p/>
	 * @param e The error
	 */
	public static void displayError(SimulatorError e) {
		System.err.println(e.getMessage());
		System.err.println("Stack trace follows:");
		e.getCause().printStackTrace();
	}

	/**
	 * Set a method to call if the simulator throws an error. This method must
	 * be static, and will be called with one parameter--the error.
	 * <p/>
	 * @param handler The method to call when an error occurs.
	 * <p/>
	 * @return The previous method, which can be called with @link{callErrorMethod()} to chain the methods together.
	 */
	public Method onError(Method handler) {
		Method oldHandler = onError;
		onError = handler;
		return oldHandler;
	}

	public Status getStatus() {
		return status;
	}

	/**
	 * Set the status and call the status change method.
	 * <p/>
	 * @param s
	 */
	public void setStatus(Status s) {
		if(onStateChange != null) {
			callStatusChangeMethod(onStateChange, s, status);
		}
		status = s;
	}

	public static void callStatusChangeMethod(Method handler, Status newStatus, Status oldStatus) {
		try {
			handler.invoke(null, newStatus, oldStatus);
		} catch(Exception e) {
			System.err.println("WARN: The simulator state change handler threw an exception:");
			e.printStackTrace();
		}
	}

	/**
	 * Set a method to be called when the status of the simulator changes.
	 * <p/>
	 * @param handler The method to call
	 * <p/>
	 * @return The previous method, which can be called with @link{callStatusChangeMethod()} to chain the methods together.
	 */
	public Method onStatusChange(Method handler) {
		Method oldHandler = onStateChange;
		onStateChange = handler;
		return oldHandler;
	}

	/**
	 * Get the error which caused the simulator to stop, or null if there was no
	 * such error.
	 * <p/>
	 * @return The error which caused the simulator to stop.
	 */
	public SimulatorError getError() {
		return error;
	}

	public Logger getLogger() {
		return log;
	}

	/**
	 * Represents the various states the simulator can be in--initalizing,
	 * running, stopped, erring, etc.
	 */
	public static class Status {

		/**
		 * Ready to run, but not actually doing anything.
		 */
		public static final Status READY = new Status(0);
		/**
		 * Instantiating the MIDlet class.
		 */
		public static final Status INITIALIZING = new Status(1);
		/**
		 * Actually running; this means that the MIDlet.startApp() class is
		 * active.
		 */
		public static final Status RUNNING = new Status(2);
		/**
		 * Finished running; exited without throwing anything.
		 */
		public static final Status STOPPED = new Status(3);
		/**
		 * Exited abnormally after throwing something.
		 */
		public static final Status ERROR = new Status(4);
		/**
		 * The watchdog is enabled but has not been fed, this is only a
		 * notification, the simulator continues running to make debugging
		 * easier.
		 */
		public static final Status WATCHDOG_NOT_FED = new Status(5);
		private int id;
		private static final String texts[] = {"Ready", "Initializing", "Running", "Stopped", "Error", "Watchdog Not Fed"};

		private Status(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return texts[id];
		}

		/**
		 *
		 * @return True if the simulator is running, initializing, or if the
		 * watchdog is not fed..
		 */
		public boolean isRunning() {
			return this == INITIALIZING || this == RUNNING || this == WATCHDOG_NOT_FED;
		}

		/**
		 *
		 * @return True if the simulator is stopped (normally or because of an
		 * error).
		 */
		public boolean isStopped() {
			return this == STOPPED || this == ERROR;
		}
	}

	private static String msg(String[] sa) {
		String temp = sa[0];
		for(int i = 1; i < sa.length; i++) {
			temp += ":" + sa[i];
		}
		return temp;
	}

	public static void msg(Class source, Thread thread, String message) {
		if(CRIO.getInstance().isDebugging()) {
			StackTraceElement[] stackTraces;
			stackTraces = thread.getStackTrace();
			String[] msg = {"msg", thread.getName(), stackTraces[2].toString(), stackTraces[3].toString(), source.getName(), message};
			log.log(Level.INFO, msg(msg));
		}
	}

	public static void fixme(Class source, Thread thread, String reason) {
		if(CRIO.getInstance().isDebugging()) {
			StackTraceElement[] stackTraces;
			stackTraces = thread.getStackTrace();
			String[] msg = {"fixme", thread.getName(), stackTraces[2].toString(), stackTraces[3].toString(), source.getName(), reason};
			log.log(Level.INFO, msg(msg));
		}
	}

	public static void err(Class source, Thread thread, String error) {
		if(CRIO.getInstance().isDebugging()) {
			StackTraceElement[] stackTraces;
			stackTraces = thread.getStackTrace();
			String[] msg = {"err", thread.getName(), stackTraces[2].toString(), stackTraces[3].toString(), source.getName(), error};
			log.log(Level.WARNING, msg(msg));
		}
	}
}
