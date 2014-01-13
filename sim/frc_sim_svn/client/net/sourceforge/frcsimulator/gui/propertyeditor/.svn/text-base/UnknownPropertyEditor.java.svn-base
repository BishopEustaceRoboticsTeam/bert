/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.gui.propertyeditor;

import java.awt.Dimension;
import javax.swing.JLabel;
import net.sourceforge.frcsimulator.internals.FrcBotSimProperty;

/**
 *
 * @author wolf
 */
class UnknownPropertyEditor extends PropertyEditor {
	@Override
	public void initialize(String key, FrcBotSimProperty property) {
		// The <html></html> forces word wrap (!)
		JLabel label = new JLabel("<html>Unknown property type: "+property.get().getClass().getCanonicalName()+"</html>");
		label.setPreferredSize(new Dimension(1,1));
		add(label);
	}

}
