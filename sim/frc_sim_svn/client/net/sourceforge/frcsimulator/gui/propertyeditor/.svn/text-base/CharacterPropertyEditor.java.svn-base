/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.gui.propertyeditor;

import java.awt.event.*;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.sourceforge.frcsimulator.internals.FrcBotSimProperty;

/**
 *
 * @author wolf
 */
public class CharacterPropertyEditor extends PropertyEditor<Character> {
	protected JTextField n_inputChar;
        protected JLabel n_decLabel,n_hexLabel,n_octLabel,n_binLabel;
	protected FrcBotSimProperty<Character> property;
	@Override
	public void initialize(String key, FrcBotSimProperty<Character> iProperty) {
		property = iProperty;
		n_inputChar = new JTextField();
                n_inputChar.setText(property.get().toString());
                n_decLabel = new JLabel("DEC: "+(int)property.get());
                n_hexLabel = new JLabel("HEX: "+Integer.toHexString((int)property.get()).toUpperCase());
                n_octLabel=new JLabel("OCT: "+Integer.toOctalString((int)property.get()));
                n_binLabel=new JLabel("BIN: "+Integer.toBinaryString((int)property.get()));
                n_inputChar.setColumns(1);
                n_inputChar.setEditable(false);
                n_inputChar.addKeyListener(new KeyAdapter(){

            @Override
            public void keyTyped(KeyEvent ke) {
                n_inputChar.setText(Character.toString(ke.getKeyChar()));
                property.set(ke.getKeyChar());
                                n_decLabel.setText("DEC: "+Integer.toString((int)ke.getKeyChar()));
                                n_hexLabel.setText("HEX: "+Integer.toHexString((int)ke.getKeyChar()).toUpperCase());
                                n_octLabel.setText("OCT: "+Integer.toOctalString((int)ke.getKeyChar()));
                                n_binLabel.setText("BIN: "+Integer.toBinaryString((int)ke.getKeyChar()));
            }
                });
		add(n_inputChar);
                add(n_hexLabel);
                add(n_octLabel);
                add(n_binLabel);
	}
}
