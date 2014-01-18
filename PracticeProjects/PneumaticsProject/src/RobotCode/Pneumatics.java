/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import java.util.Vector;


/**
 *
 * @author bert3
 */
public class Pneumatics {
  
  Compressor compressor_;
  Vector double_solenoids_list_;

  Pneumatics(int pressure_switch_channel, int compressor_relay_channel) {

    // pressure_switch_channel - digital input port to pressure switch that monitors the accumulator pressure
    // compressor_relay_channel - digital output port to a digital (spike) relay that controls power to the compressor
    compressor_ = new Compressor(pressure_switch_channel, compressor_relay_channel);
    double_solenoids_list_ = new Vector();
    
  }
    
  
  public void startCompressor() {
    compressor_.start();
  }
  
  public void stopCompressor() {
    compressor_.stop();
  }

  // Push a new solenoid object onto our vector of solenoids.
  public void addNewDoubleSolenoid(int forward_channel, int reverse_channel) {
    DoubleSolenoid solenoid = new DoubleSolenoid(forward_channel, reverse_channel);
    double_solenoids_list_.addElement(solenoid);
  }

  public void moveSolenoidForward(int index) {
    ((DoubleSolenoid)double_solenoids_list_.elementAt(index)).set(DoubleSolenoid.Value.kForward);
  }

  public void moveSolenoidBackward(int index) {
    ((DoubleSolenoid)double_solenoids_list_.elementAt(index)).set(DoubleSolenoid.Value.kReverse);
  }

  public void turnSolenoidOff(int index) {
    ((DoubleSolenoid)double_solenoids_list_.elementAt(index)).set(DoubleSolenoid.Value.kOff);
  }

  public int getNumSolenoids() {
    return double_solenoids_list_.size();
  }
}
