/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode;

/**
 *
 * @author bert2
 */

public class Autonomous {

  //double k_desired_shooting_distance = 0.914; //0.914m = three ft
  double k_desired_shooting_distance = 2; // meters , tested 022614
double k_autonomous_velocity = 0.3;

StateEstimator state_;
MecDrive drive_;

    Autonomous(StateEstimator state, MecDrive drive){
        state_ = state;
        drive_ = drive;
        
    }
    
    public boolean driveToShoot(){
        double dist = state_.getDistanceMetersToWall();
        double distFromDisiredShootingDistance = dist - k_desired_shooting_distance;
        if(distFromDisiredShootingDistance >=  0.0){
            //while the distance is greater then 0 meters drive at toward the goal
            drive_.driveWithShooterInFront(k_autonomous_velocity);
            return false;
        }
        else {
            //stop the robot at the disired distance
            drive_.driveWithShooterInFront(0.0);
            return true;
        }
    
  }    
    
    
    
    
    
}
