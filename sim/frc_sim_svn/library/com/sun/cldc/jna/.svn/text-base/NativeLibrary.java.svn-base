/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.cldc.jna;

import net.sourceforge.frcsimulator.internals.CRIO;
import net.sourceforge.frcsimulator.mistware.Simulator;

/**
 * Replacement for native library wrappers which throws exceptions indicating
 * they need to be replaced.
 * <p/>
 * @author wolf
 */
public class NativeLibrary {

	public static NativeLibrary getDefaultInstance() {
		Simulator.fixme(NativeLibrary.class, Thread.currentThread(), "NativeLibrary should not be used");
		return new NativeLibrary();//@TODO should not be used
	}

	public Function getFunction(String funcName) {
		Simulator.fixme(NativeLibrary.class, Thread.currentThread(), "NativeLibrary should not be used");
		return new Function();//@TODO should not be used
	}

	public BlockingFunction getBlockingFunction(String funcName) {
		Simulator.fixme(NativeLibrary.class, Thread.currentThread(), "NativeLibrary should not be used");
		return new BlockingFunction() {
			public void run() {
				Simulator.fixme(BlockingFunction.class, Thread.currentThread(), "BlockingFuntion called through NativeLibrary");
			}
		};//@TODO should not be used
	}
}
