/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.squawk.io;

import java.io.*;
import javax.microedition.io.Connection;

/**
 *
 * @author wolf
 */
public class ConnectionBase implements Connection {

	@Override
	public void close() throws IOException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public DataOutputStream openDataOutputStream() throws IOException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public OutputStream openOutputStream() throws IOException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
