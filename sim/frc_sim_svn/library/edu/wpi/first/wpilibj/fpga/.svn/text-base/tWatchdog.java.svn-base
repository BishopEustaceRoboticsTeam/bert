/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.fpga;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Watchdog;
import net.sourceforge.frcsimulator.internals.CRIO;
import net.sourceforge.frcsimulator.internals.SimulatedBot;
import net.sourceforge.frcsimulator.mistware.Simulator;

/**
 * Pretends to be the FPGA watchdog.
 * <p/>
 * @author wolf
 */
public class tWatchdog extends tSystem {

	private static long expiration;
	private static boolean immortal = true;
	private static boolean fed = true;
	private static boolean dead = false;
	private static long lastFed;
	private static final double expirationScale = 1000 / (SensorBase.kSystemClockTicksPerMicrosecond * 1e6);
	private static Thread softWatchdog = new Thread() {
		@Override
		public void run() {
			while(immortal) {
			}
			while((fed && !dead) || !DriverStation.getInstance().isEnabled()) {
				fed = !DriverStation.getInstance().isEnabled();
				try {
					sleep((long)(expiration * expirationScale));
				} catch(InterruptedException e) {
					Simulator.err(tWatchdog.class, softWatchdog, "The Watchdog was interrupted when it was trying to sleep, HOW RUDE!");
				}
			}
			if(!immortal) {
				dead = true;
				Simulator.getInstance().setStatus(Simulator.Status.WATCHDOG_NOT_FED);
			}
			//@TODO print a stack trace of the main thread for ease of debugging a watchdog not fed error.
		}
	};

	public tWatchdog() {
		super();
		softWatchdog.start();
	}

	@Override
	protected void finalize() {
		super.finalize();
	}
	public static final int kNumSystems = 1;

	public static long readExpiration() {
		return expiration;
	}

	public static boolean readImmortal() {
		return immortal;
	}

	public static boolean readStatus_Alive() {
		return !dead;
	}

	public static short readStatus_DisableCount() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public static short readStatus_SysDisableCount() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public static boolean readStatus_SystemActive() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public static int readStatus() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public static long readTimer() {
		return (long)((System.currentTimeMillis() - lastFed) * expirationScale);
	}

	public static void strobeFeed() {
		fed = true;
		lastFed = System.currentTimeMillis();
	}

	public static void strobeKill() {
		dead = true;
	}

	public static void writeExpiration(long value) {
		expiration = value;
	}

	public static void writeImmortal(boolean value) {
		immortal = value;
	}
}
