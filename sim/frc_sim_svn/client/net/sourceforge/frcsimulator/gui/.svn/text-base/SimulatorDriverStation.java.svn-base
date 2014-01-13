package net.sourceforge.frcsimulator.gui;

import edu.wpi.first.wpilibj.communication.FRCCommonControlData;
import edu.wpi.first.wpilibj.communication.FRCControl;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.NumberFormat;
import javax.swing.*;

/**
 * A Clone of the DriverStation used in the competition.
 * @author benjamin
 */
public class SimulatorDriverStation  extends JFrame{
    private JRadioButton autonomous   = new JRadioButton("Autonomous");
    private JRadioButton teleoperated = new JRadioButton("Teleoperated");
    private JRadioButton disabled     = new JRadioButton("Disabled");
    private ButtonGroup status = new ButtonGroup();
    private JFormattedTextField teamNumber = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private ButtonGroup alliance = new ButtonGroup();
    private JRadioButton red = new JRadioButton("Red");
    private JRadioButton blue = new JRadioButton("Blue");
    private JLabel allianceLabel = new JLabel("Alliance:");
    private JComponent positionStuff = new JComponent(){};
    private JLabel positionLabel = new JLabel("Position:");
    private ButtonGroup position = new ButtonGroup();
    private JRadioButton one   = new JRadioButton("1");
    private JRadioButton two   = new JRadioButton("2");
    private JRadioButton three = new JRadioButton("3");
    private JTextArea dataLCD = new JTextArea();
    public SimulatorDriverStation(String testcase){
	super("FRC Simulator DriverStation - " + testcase);
	this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setLayout(null);
	dataLCD.setEditable(false);
	dataLCD.setColumns(21);
	dataLCD.setRows(6);
	positionStuff.setLayout(new FlowLayout());
	    disabled.setSelected(true);
	    red.setSelected(true);
	    one.setSelected(true);
	    teamNumber.setColumns(5);
	    teamNumber.setText("0000");
	    autonomous.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
		    dataLCD.setText("");
		    FRCCommonControlData.getInstance().getSimProperties().get("enabled").set(false);
		    FRCCommonControlData.getInstance().getSimProperties().get("autonomous").set(true);
		    FRCCommonControlData.getInstance().getSimProperties().get("enabled").set(true);
		}
		
	    });
	    teleoperated.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
		    dataLCD.setText("");
		    FRCCommonControlData.getInstance().getSimProperties().get("enabled").set(false);
		    FRCCommonControlData.getInstance().getSimProperties().get("autonomous").set(false);
		    FRCCommonControlData.getInstance().getSimProperties().get("enabled").set(true);
		}
		
	    });
	    disabled.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
		    dataLCD.setText("");
		    FRCCommonControlData.getInstance().getSimProperties().get("enabled").set(false);
		}
		
	    });
	    red.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    FRCCommonControlData.getInstance().getSimProperties().get("alliance").set('R');
		}
	    });
	    blue.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    FRCCommonControlData.getInstance().getSimProperties().get("alliance").set('B');
		}
	    });
	    one.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    FRCCommonControlData.getInstance().getSimProperties().get("position").set('1');
		}
	    });
	    two.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    FRCCommonControlData.getInstance().getSimProperties().get("position").set('2');
		}
	    });
	    three.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    FRCCommonControlData.getInstance().getSimProperties().get("position").set('3');
		}
	    });
	    teamNumber.addPropertyChangeListener("value", new PropertyChangeListener(){

	    @Override
	    public void propertyChange(PropertyChangeEvent evt) {
		FRCCommonControlData.getInstance().getSimProperties().get("team").set(Integer.parseInt(teamNumber.getText()));
	    }
		
	    });
	    status.add(autonomous);
	    status.add(teleoperated);
	    status.add(disabled);
	    alliance.add(red);
	    alliance.add(blue);
	    position.add(one);
	    position.add(two);
	    position.add(three);
	    setSize(400,120);
	    positionStuff.setBounds(teamNumber.getPreferredSize().width/4,-5,getWidth()-teamNumber.getPreferredSize().width,one.getPreferredSize().height);
	    positionStuff.add(positionLabel);
	    positionStuff.add(one);
	    positionStuff.add(two);
	    positionStuff.add(three);
	    add(positionStuff);
	    teamNumber.setBounds(0,0,teamNumber.getPreferredSize().width,teamNumber.getPreferredSize().height);
	    add(teamNumber);
	    allianceLabel.setBounds(teleoperated.getPreferredSize().width+5,25,allianceLabel.getPreferredSize().width,allianceLabel.getPreferredSize().height);
	    add(allianceLabel);
	    red.setBounds(teleoperated.getPreferredSize().width,40,red.getPreferredSize().width,red.getPreferredSize().height);
	    add(red);
	    blue.setBounds(teleoperated.getPreferredSize().width,60,blue.getPreferredSize().width,blue.getPreferredSize().height);
	    add(blue);
	    autonomous.setBounds(0,20,autonomous.getPreferredSize().width,autonomous.getPreferredSize().height);
	    add(autonomous);
	    teleoperated.setBounds(0,40,teleoperated.getPreferredSize().width,teleoperated.getPreferredSize().height);
	    add(teleoperated);
	    disabled.setBounds(0,60,disabled.getPreferredSize().width,disabled.getPreferredSize().height);
	    add(disabled);
	    dataLCD.setLocation(blue.getX()+blue.getWidth()+10,positionStuff.getHeight()+5);
	    dataLCD.setSize(dataLCD.getPreferredSize());
	    add(dataLCD);
	    setSize(dataLCD.getX()+dataLCD.getWidth()+5,dataLCD.getY()+dataLCD.getHeight()+20);
	    setLocation(getX()+500,getY());
	    setVisible(true);
	    repaint();
    }
    public void paint(Graphics g){
	super.paint(g);
	boolean noNPE = FRCCommonControlData.getInstance() != null;
	    autonomous.setEnabled(noNPE);
	    teleoperated.setEnabled(noNPE);
	    disabled.setEnabled(noNPE);
	    teamNumber.setEnabled(noNPE);
	    red.setEnabled(noNPE);
	    blue.setEnabled(noNPE);
	    one.setEnabled(noNPE);
	    two.setEnabled(noNPE);
	    three.setEnabled(noNPE);
	    dataLCD.setText(FRCControl.dataLCDText);
	    try {
	    Thread.sleep(100);
	} catch (InterruptedException ex) {}
	repaint();
    }
}
