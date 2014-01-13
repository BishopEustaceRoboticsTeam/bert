/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.internals;

/**
 *
 * @author benjamin
 */
public class RobotStatusDSReadable {

	private static String status;

	public static void setStatus(String status) {
		RobotStatusDSReadable.status = status;
	}

	public static String getStatus() {
		return status;
	}
}
