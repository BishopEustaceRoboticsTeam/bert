/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.gui.propertyeditor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.sourceforge.frcsimulator.internals.FrcBotSimProperty;

/**
 *
 * @author wolf
 */
public class StringPropertyEditor extends PropertyEditor<String> {
	protected JTextArea n_textArea;
	protected FrcBotSimProperty<String> property;
	@Override
	public void initialize(String key, FrcBotSimProperty<String> iProperty) {
		property = iProperty;
		n_textArea = new JTextArea(property.get());
                n_textArea.addKeyListener(new KeyAdapter(){
                    @Override
                    public void keyTyped(KeyEvent ke){
                        property.set(n_textArea.getText());
                    }
                });
                add(n_textArea);
	}
}
