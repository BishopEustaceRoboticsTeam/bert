/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.internals;

import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author wolf
 */
public class FrcBotSimProperty<T> {

	public boolean setByCode, physical;
	protected T value;
	protected ArrayList<ChangeListener> changeListeners = new ArrayList<ChangeListener>();

	public FrcBotSimProperty() {
	}

	public FrcBotSimProperty(T defaultValue) {
		value = defaultValue;
	}

	public T get() {
		return value;
	}

	public void set(T to) {
		if(value != to) {
			value = to;
			triggerChange();
		}
	}

	protected void triggerChange() {
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener c : changeListeners) {
			c.stateChanged(event);
		}
	}

	public void addChangeListener(ChangeListener c) {
		changeListeners.add(c);
	}

	public void removeChangeListener(ChangeListener c) {
		changeListeners.remove(c);
	}
}
