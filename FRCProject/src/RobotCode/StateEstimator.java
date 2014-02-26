
package RobotCode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.HiTechnicColorSensor;
import edu.wpi.first.wpilibj.SensorBase;


public class StateEstimator {
    
    static final double kDISTANCESONARFACTOR = 0.55; 
    // this value is measured on 2/24/14 by awesome people
    static final double kMINCOLORTHRESH = 50.0;
    static final double kINVALIDCOLORTHRESH = 30.0;

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
    
    public StateEstimator(int sonarsensor_port, int team_color_port_number){
   
        analogchannel = new AnalogChannel(sonarsensor_port);
        colorsensor_ = new HiTechnicColorSensor(SensorBase.getDefaultDigitalModule());
        // Turn off the LED until we are ready to use it.
        colorsensor_.setMode(HiTechnicColorSensor.tColorSensorMode.kPassive); 
        
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
    
    public void getNXTColor(){
        HiTechnicColorSensor.RGB color= colorsensor_.getRGB();
	if ((color.getRed() > kMINCOLORTHRESH) &&
	    (color.getGreen() < kINVALIDCOLORTHRESH) &&
	    (color.getBlue() < kINVALIDCOLORTHRESH)) {
	  // Red is high, others are low, assume Red detection.

	} else if ((color.getBlue() > kMINCOLORTHRESH) &&
		   (color.getRed() < kINVALIDCOLORTHRESH) &&
		   (color.getGreen() < kINVALIDCOLORTHRESH)) {
	  // Blue is high, others are low, assume Blue detection.

	} else {
	  // Not a state we prefer, consider invalid color
	}

        //System.out.println("red = " +color.getRed()+ " green= " +color.getGreen()+" blue= " +color.getBlue() );
	
    }
    
    
    public boolean getDriveMode(){
        return drive_mode;
    }
    public void setDriveMode(boolean driveMode){
        drive_mode = driveMode;
        
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
