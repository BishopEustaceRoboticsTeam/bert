
package RobotCode;

import edu.wpi.first.wpilibj.DigitalInput;




public class StateEstimator {
    static final int team_color_port_number = 4;
    
    
    // enum for red and blue
    boolean team_color_;  // (false)red or (true)blue
    
    double x_meters_;
    double y_meters_;
    double yaw_radians_;
    boolean drive_mode = true;
    boolean ball_in_posession_;
      
    // more states
    
   public StateEstimator(){
    
     determineTeamColor(team_color_port_number);
      if(team_color_){
        
            System.out.println("We are blue");
            
        }else{ 
          
          System.out.println("We are red");
          
      }
      
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
