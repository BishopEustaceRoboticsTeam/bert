
package RobotCode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.HiTechnicColorSensor;
import edu.wpi.first.wpilibj.SensorBase;


public class StateEstimator {
    
    static final double kDISTANCESONARFACTOR = 0.55; 
    // this value is measured on 2/24/14 by awesome people
    // enum for red and blue
    boolean team_color_;  // (false)red or (true)blue
    
    double x_meters_;
    double y_meters_;
    double yaw_radians_;
    boolean drive_mode = true;
    boolean ball_in_posession_;
    double dis_to_wall_meters_;
    // more states
    
    AnalogChannel analogchannel;
    HiTechnicColorSensor colorsensor_;
    DigitalInput bumpSwitch_;
    
    public StateEstimator(int sonarsensor_port, int team_color_port_number, int bumb_switch_port){
   
        analogchannel = new AnalogChannel(sonarsensor_port);
        colorsensor_ = new HiTechnicColorSensor(SensorBase.getDefaultDigitalModule());
        bumpSwitch_ = new DigitalInput(bumb_switch_port);
        
        determineTeamColor(team_color_port_number);
      
        if(team_color_) { 
            System.out.println("We are blue");
        }else{ 
          System.out.println("We are red");
        }
    }
   
    public double getDistanceMetersToWall(){    
        return (double)analogchannel.getValue() * kDISTANCESONARFACTOR * 0.0254;
    } 
    
    public void  getZoneColor(){
        HiTechnicColorSensor.RGB color= colorsensor_.getRGB();
        System.out.println("red = " +color.getRed()+ " green= " +color.getGreen()+" blue= " +color.getBlue() );
    }
    
    
    public boolean getDriveMode(){
        return drive_mode;
    }
    public void setDriveMode(boolean driveMode){
        drive_mode = driveMode;
        
    }
    public boolean getBumpSwitchState(){
        
        return bumpSwitch_.get();
    }
    
    
    
    
            
            
            
    public boolean getTeamColor()
    {
        return team_color_;
    }
    public double getXMeters()
    {
        return x_meters_;
    }
    public double getY()
    {
        return y_meters_;
    }
    public double getYaw()
    {
        return yaw_radians_;
    }
    public boolean getPossession()
    {
        return ball_in_posession_;
    }
    
    private void determineTeamColor(int port_number)
    {
       
        DigitalInput digital = new DigitalInput(port_number);
        team_color_ = digital.get();
         
        
              
    }
   
    
    public void setXMeters(double x)
    {
        
        x_meters_  = x;
    }
    
    
    
   
    
    
}
