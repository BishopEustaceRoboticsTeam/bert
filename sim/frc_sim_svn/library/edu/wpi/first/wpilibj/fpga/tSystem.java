/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.fpga;

import com.ni.rio.NiRioStatus;
import java.util.ArrayList;

/**
 *
 * @author wolf
 */
public abstract class tSystem implements ExpectedFPGASignature {

	public static NiRioStatus status = new NiRioStatus();
	protected static int m_DeviceHandle = 0;

	protected tSystem() {
		if(m_DeviceHandle == 0) {
			System.out.print("FPGA Hardware GUID: ");
			printGUID(kExpectedFPGASignature);
			System.out.println();
			System.out.print("FPGA Software GUID: ");
			printGUID(kExpectedFPGASignature);
			System.out.println();
		}
		m_DeviceHandle++;
	}

	private static void printGUID(int guid[]) {
		System.out.print("0x");
		for(int i = 0; i < 4; i++) {
			long longVar = guid[i];
			String word = Long.toString(longVar & 0xFFFFFFFFL, 16);
			while(word.length() < 8) {
				word = "0" + word;
			}
			System.out.print(word);
		}
	}

	@Override
	protected void finalize() {
	}

	public void Release() {
	}
}
