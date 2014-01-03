package RobotCode;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Gyro;

public class Sensors {
    AnalogChannel IRLeft;
    AnalogChannel IRRight;
    AnalogChannel IRFront;
    AnalogChannel IRBack;
    AnalogChannel testSensor;
    DigitalInput goalFinder;
    double OFFSETIRL_R = 5;
    double OFFSETIRF = 3;
    double OFFSETIRB = 17.5;
    ADXL345_I2C accel = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k2G);
    double accelX, accelY, accelZ; 
    I2C i2c;
    //double gyroAngle;
    
  
 
    

    Sensors(int IRLeftPort, int IRRightPort, int IRFrontPort, int IRBackPort, int goalFinderPort, int TestPort) {

        IRLeft = new AnalogChannel(IRLeftPort);
        IRRight = new AnalogChannel(IRRightPort);
        IRFront = new AnalogChannel(IRFrontPort);
        IRBack = new AnalogChannel(IRBackPort);
        goalFinder = new DigitalInput(goalFinderPort);
        testSensor = new AnalogChannel(TestPort);

    }

    public double getIRLeftInches() {

        double x = (double) IRLeft.getAverageValue();
        double inches = 0.00000000506 * x * x * x * x
                + -0.0000072 * x * x * x
                + 0.00371 * x * x
                + -0.84147 * x
                + 78.99;
        return inches - OFFSETIRL_R;
//          return x;
    }

    public double getIRRightInches() {
        double x = (double) IRRight.getAverageValue();
        double inches = 0.00000000252 * x * x * x * x
                + -0.00000395 * x * x * x
                + 0.00225 * x * x
                + -0.57017 * x
                + 61.268;

        return inches - OFFSETIRL_R;
    }

    public double getIRFrontInches() {

        double x = (double) IRFront.getAverageValue();
//        double inches = 0.0000000632*x*x*x*x+
//                -0.000117*x*x*x+
//                0.0813*x*x+
//                -24.94*x+
//                2930.862;
//        return inches - OFFSETIRF;
        double inches = -0.000000000278049775725601 * x * x * x * x * x
                + 0.0000006622565994762741 * x * x * x * x
                + -0.0006298120323435796 * x * x * x
                + 0.2995356399624143 * x * x
                + -71.56538544269278 * x
                + 6961.273704136952;
        return inches - OFFSETIRF;
    }

    public double getIRBackInches() {

        double x = (double) IRBack.getAverageValue();
//        double inches = 0.0000000357*x*x*x*x+
//                -0.000068*x*x*x+
//                0.04945*x*x+
//                -15.9571*x+
//                2010.1819;
//        return inches - OFFSETIRB;
        return x;
    }

    public int getTestSensor() {

        int Data;
        Data = testSensor.getValue();
        return Data;
    }

    public boolean isThereAGoal() {
        return goalFinder.get();
    }

    public int getTestSensorAvg() {

        int Data;
        Data = testSensor.getAverageValue();
        return Data;
    }

    public void updateIRSensorsDashboard() {
        //there should be at least a 0.2 second delay
        SmartDashboard.putNumber("Left IR (inches)", getIRLeftInches());
        SmartDashboard.putNumber("Right IR (inches)", getIRRightInches());
        SmartDashboard.putNumber("Front IR (inches)", getIRFrontInches());
        SmartDashboard.putNumber("Back IR (inches)", getIRBackInches());
        if (goalFinder.get()) {
            SmartDashboard.putString("Goal Finder", "Goal is there!");
        } else {
            SmartDashboard.putString("Goal Finder", "Goal is not there");
        }

    }
    public double lagFiltered (double unfiltered_inches, double prior, double k){
        return ((k * unfiltered_inches) + ((1-k) * prior));
    }
    public void printAccelData() {
        
            i2c.setCompatabilityMode(true); // this is vital to get sensor to work properly
            //i2c is much faster with this set to false(default setting) but the ADXL345 wont work
            accelX = accel.getAcceleration(ADXL345_I2C.Axes.kX);
            accelY = accel.getAcceleration(ADXL345_I2C.Axes.kY);
            accelZ = accel.getAcceleration(ADXL345_I2C.Axes.kZ);
            System.out.println("x acceleration in gs " + accelX);
            System.out.println("y acceleration in gs " + accelY);
            System.out.println("z acceleration in gs " + accelZ);
        
        
    }
    
}