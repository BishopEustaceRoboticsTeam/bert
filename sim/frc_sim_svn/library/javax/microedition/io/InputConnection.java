/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.microedition.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author wolf
 */
public interface InputConnection extends Connection {

	InputStream openInputStream() throws IOException;

	DataInputStream openDataInputStream() throws IOException;
}
