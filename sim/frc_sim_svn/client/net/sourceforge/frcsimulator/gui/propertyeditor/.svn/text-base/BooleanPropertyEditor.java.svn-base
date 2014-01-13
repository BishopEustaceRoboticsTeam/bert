/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.gui.propertyeditor;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.sourceforge.frcsimulator.internals.FrcBotSimProperty;

/**
 *
 * @author wolf
 */
public class BooleanPropertyEditor extends PropertyEditor<Boolean> {
	protected JCheckBox checkbox;
	protected FrcBotSimProperty<Boolean> property;
	@Override
	public void initialize(String key, FrcBotSimProperty<Boolean> iProperty) {
		property = iProperty;
		checkbox = new JCheckBox(key);
                checkbox.setSelected(property.get());
		checkbox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				property.set(checkbox.isSelected());
			}
		});
		add(checkbox);
	}
}
