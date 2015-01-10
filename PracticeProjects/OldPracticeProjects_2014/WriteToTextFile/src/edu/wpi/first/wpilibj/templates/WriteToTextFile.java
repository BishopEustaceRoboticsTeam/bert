/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.microedition.io.Connector;


import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.io.BufferedWriter;
import com.sun.squawk.microedition.io.FileConnection;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class WriteToTextFile extends SimpleRobot {
    
    public static void writeToFile(String fileName, String text){
        String url = "file:///";
        try{
        FileConnection c = (FileConnection) Connector.open(url + fileName);
        OutputStreamWriter writer = new OutputStreamWriter(c.openOutputStream());
        writer.write(text);
        c.close();
        
        } catch(IOException e) {
            e.printStackTrace();
            
        }
    }
    
    
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        String text = "this is the text that i am writing to the file";
        writeToFile("text.txt", text);
        System.out.println("I think it worked!!!!!!!!!");
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
