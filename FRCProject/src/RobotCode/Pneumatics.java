/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode;

import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
import java.util.Vector;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author bert3
 */
public class Pneumatics {
  
  Compressor compressor_;
  //Vector double_solenoids_list_;
  Vector single_solenoids_list_;
  Vector solenoids_states_;
  
  Pneumatics(int pressure_switch_channel, int compressor_relay_channel) {

    // pressure_switch_channel - digital input port to pressure 
    // switch that monitors the accumulator pressure, this gives
    // us data about when the tank is full so we can turn off the
    // compressor
    // compressor_relay_channel - relay output port to a 
    // digital (spike) relay that controls power to the 
    // compressor (remember, this is relay output, not digital 
    // output
    compressor_ = new Compressor(pressure_switch_channel, compressor_relay_channel);
    //double_solenoids_list_ = new Vector();
    single_solenoids_list_= new Vector();
    solenoids_states_ = new Vector();
  }
    
  
  public void startCompressor() {
    System.out.println("Starting Compressor...");
    compressor_.start();
  }
  
  public void stopCompressor() {
    if (compressor_.enabled()) {
      System.out.println("Stopping Compressor...");
      compressor_.stop();
    }
  }

  public void enableLiveWindowMode() {
    System.out.println("Enable live window mode...");
    compressor_.startLiveWindowMode();
  }
  
  public void disableLiveWindowMode() {
    System.out.println("Disable live window mode...");
    compressor_.stopLiveWindowMode();
  }

  public boolean pressureSwitchValue() {
    return compressor_.getPressureSwitchValue();
  }

  // Push a new solenoid object onto our vector of solenoids.
  //public void addNewDoubleSolenoid(int forward_channel, int reverse_channel) {
  //  DoubleSolenoid solenoid = new DoubleSolenoid(forward_channel, reverse_channel);
  //  double_solenoids_list_.addElement(solenoid);
  //}

   public int addNewSingleSolenoid(int forward_channel){
      Solenoid solenoid = new Solenoid(forward_channel);
      single_solenoids_list_.addElement(solenoid);
      return single_solenoids_list_.size() -1;
   }
  public void moveSingleSolenoidOut(int index) {
    ((Solenoid)single_solenoids_list_.elementAt(index)).set(true);
  }

  public void moveSingleSolenoidIn(int index) {
    ((Solenoid)single_solenoids_list_.elementAt(index)).set(false);
  }

  public int getNumSolenoids() {
    return single_solenoids_list_.size();
   
  }
}
