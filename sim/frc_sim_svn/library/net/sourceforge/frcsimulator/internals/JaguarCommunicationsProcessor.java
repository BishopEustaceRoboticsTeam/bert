/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.internals;

import java.util.LinkedList;

/**
 * @todo What is this?
 * @author benjamin
 */
public class JaguarCommunicationsProcessor implements FrcBotSimComponent {

	private static LinkedList<byte[]>[] m_kdataInputStreams = new LinkedList[63];
	private static LinkedList<byte[]>[] m_kdataOutputStreams = new LinkedList[63];
	private static int[] m_kmessageIDs = new int[63];

	@Override
	public FrcBotSimProperties getSimProperties() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
