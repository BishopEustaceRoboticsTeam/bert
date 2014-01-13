/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.communication.ModulePresence;
import net.sourceforge.frcsimulator.internals.CRIO;
import net.sourceforge.frcsimulator.internals.CRIOModule;
import net.sourceforge.frcsimulator.internals.ModuleException;
import net.sourceforge.frcsimulator.mistware.Simulator;

/**
 * Base class for AnalogModule and DigitalModule.
 * <p/>
 * @author dtjones
 */
public class Module extends SensorBase {

	/**
	 * An array holding the object representing each module
	 */
//    protected static Module[] m_modules = new Module[ModulePresence.ModuleType.kSolenoid.getValue() * ModulePresence.kMaxModuleNumber + (ModulePresence.kMaxModuleNumber - 1)];
	/**
	 * The module number of the module
	 */
	protected CRIOModule m_hwModule;
	protected int m_moduleNumber;
	protected ModulePresence.ModuleType m_moduleType;

	/**
	 * Constructor.
	 * <p/>
	 * @param moduleNumber The number of this module (1 or 2).
	 */
	protected Module(ModulePresence.ModuleType moduleType, final int moduleNumber) {
		if(!ModulePresence.getModulePresence(moduleType, moduleNumber - 1)) {
			throw new RuntimeException("You are not simulating " + moduleType + " module " + moduleNumber + ".");
		} else {
			try {
				m_hwModule = CRIO.getInstance().getModule(moduleType.getValue(), moduleNumber - 1);
			} catch(ModuleException ex) {
				throw new RuntimeException("Strange, a ModuleException was thrown after the ModulePresence check, WHAT DID YOU DO‽‽‽ INFO:" + moduleType.getValue() + ":" + moduleNumber);
			}
			m_moduleNumber = moduleNumber;
		}
	}

	/**
	 * Gets the module number associated with a module.
	 * <p/>
	 * @return The module's number.
	 */
	public int getModuleNumber() {
		return m_moduleNumber;
	}

	/**
	 * Gets the module type associated with a module.
	 * <p/>
	 * @return The module's type.
	 */
	public ModulePresence.ModuleType getModuleType() {
		return m_moduleType;
	}

	/**
	 * Static module singleton factory.
	 * <p/>
	 * @param moduleType The type of the module represented.
	 * @param moduleNumber The module index within the module type.
	 * <p/>
	 * @return the module
	 */
	public static Module getModule(ModulePresence.ModuleType moduleType, int moduleNumber) {
		return new Module(moduleType, moduleNumber);
	}
}
