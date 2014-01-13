/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sun.cldc.jna;

import java.util.concurrent.*;

/**
 *
 * @author wolf
 */
public class TaskExecutor extends ThreadPoolExecutor {

	static ArrayBlockingQueue m_arrayBlockingQueue = new ArrayBlockingQueue(25);

	public TaskExecutor(String name) {
		super(20, Integer.MAX_VALUE, Long.MAX_VALUE, TimeUnit.MILLISECONDS, m_arrayBlockingQueue);
	}

	public void cancelTaskExecutor() {
		shutdown();
	}

	public int deleteTaskExecutor() {
		finalize();
		return 0;
	}

	public void stopTaskExecutor() {
		shutdown();
		finalize();
	}
}
