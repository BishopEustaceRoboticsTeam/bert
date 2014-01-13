/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.frcsimulator.internals;

/**
 * Note: A component should call SimulatedBot.addSimComponent(this); in the
 * constructor.
 * <p/>
 * @author wolf
 */
public interface FrcBotSimComponent {

	public abstract FrcBotSimProperties getSimProperties();
}
