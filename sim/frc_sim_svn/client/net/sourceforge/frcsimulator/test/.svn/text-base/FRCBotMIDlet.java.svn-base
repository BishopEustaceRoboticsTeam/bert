package net.sourceforge.frcsimulator.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.communication.FRCControl;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * The simplest possible robot which extends RobotBase. If this throws
 * exceptions (which it currently does) the simulator won't work until they're
 * cleared up.
 */
public class FRCBotMIDlet extends MIDlet {

	private final Watchdog m_watchdog = Watchdog.getInstance();
	protected final DriverStation m_ds;
	public FRCBotMIDlet() {
		m_watchdog.setEnabled(false);
		m_ds = DriverStation.getInstance();
	}
	@Override
	protected void startApp() throws MIDletStateChangeException {
        Watchdog.getInstance().setExpiration(0.1);
        Watchdog.getInstance().setEnabled(false);
        //FRCControl.observeUserProgramStarting();
//        UsageReporting.report(UsageReporting.kResourceType_Language, UsageReporting.kLanguage_Java);

		System.out.println("MIDlet started");
	}
	
}
