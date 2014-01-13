/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.gui.propertyeditor;

import javax.swing.JLabel;
import net.sourceforge.frcsimulator.internals.FrcBotSimProperty;

/**
 *
 * @author wolf
 */
public class NullPropertyEditor extends PropertyEditor {
	@Override
	public void initialize(String key, FrcBotSimProperty property) {
		add(new JLabel("No valid property selected."));
	}
}
