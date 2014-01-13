/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.microedition.midlet;

/**
 *
 * @author wolf
 */
public abstract class MIDlet {

	/**
	 * An addition to the MIDlet class required for the simulator to be able to
	 * start the MIDlet, since startApp() is protected.
	 * <p/>
	 * @throws MIDletStateChangeException
	 */
	public final void runMIDlet() throws MIDletStateChangeException {
		startApp();
	}

	protected abstract void startApp() throws MIDletStateChangeException;
}
