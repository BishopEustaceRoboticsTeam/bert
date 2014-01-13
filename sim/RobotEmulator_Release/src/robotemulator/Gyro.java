/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotemulator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author aubrey
 */
public class Gyro {
    double angle;


     private final int JSHEIGHT = 500;//joy stick area height
    private final int JSWIDTH = 500;//joy stick area width

    private double x, y;
    private int xpos, ypos;

    private boolean mouseClicked = false;
    private boolean trigger = false;
    
    private JFrame frame;
    private Grid grid;
    
    
    public Gyro(int channel) {
        frame = new JFrame("Gyro Emulator: " + channel);
        
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(JSWIDTH, JSHEIGHT- 50));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grid = new Grid();
        frame.add(grid, BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);        
    
    }
    
    public double getAngle() {
        double aangle = (-Math.atan2(ypos - 250, xpos-250))*(180/Math.PI);
        angle = (aangle <= 0? aangle + 360: aangle);
        return angle;
    }
    
    public void reset() {
        angle = 0;
    }
    
    class Grid extends JPanel implements MouseListener, MouseMotionListener {
        Grid() {
            addMouseListener(this);
            addMouseMotionListener(this);
            
        }
        @Override
        public void paintComponent(Graphics g) {
            g.setFont(new Font("Helvetica", Font.BOLD, 14));
            
            //clears graph.
            g.setColor(Color.white);
            g.fillRect(0, 0, grid.getWidth(), grid.getHeight());
            g.setColor(Color.black);
            
            //checks if trigger is set and draws a red filled rectangle if it is.
            if (trigger) {
                g.setColor(Color.red);
                g.fillRect(xpos-20, ypos-20, 40, 40);
                g.setColor(Color.black);
            }
            
            //draws x and y axis and bottom border of grid.
            g.drawLine(0, JSHEIGHT/2, getWidth(), JSHEIGHT/2);
            g.drawLine(getWidth()/2, 0, getWidth()/2, JSHEIGHT);
            g.drawLine(0, JSHEIGHT, getWidth(), JSHEIGHT);
   
            //drawing joystick and mouse positions
            double aangle = (-Math.atan2(ypos - 250, xpos-250))*(180/Math.PI);
            g.drawString("Angle: "+ (aangle <= 0? aangle + 360: aangle), 5, 20);
            g.drawString("Angle is " + (mouseClicked?"":"not ") + "locked", 5, 60);
            
            g.drawOval(100, 100, 300, 300);
            
            drawBox(xpos, ypos, g);
        }
        
        public void drawBox(int x, int y, Graphics g) {
            y = (int) -(150 * Math.sin(-Math.atan2(ypos - 250, xpos-250))) + 250;
            x = (int) (150 * Math.cos(-Math.atan2(ypos - 250, xpos-250))) + 250;
            g.drawRect(x - 20, y-20, 40, 40); 
            g.drawLine(x, y-20, x, y+20);
            g.drawLine(x-20, y, x+20, y);
        }
        
        public void determineMousePos(MouseEvent e) {
            if(!mouseClicked) {
                xpos = e.getX();
                ypos = e.getY();
            }

            if (ypos > JSHEIGHT) {
                ypos = JSHEIGHT;
            }

            x = ((double)(xpos-JSHEIGHT/2.0)/(JSHEIGHT/2.0));
            y = ((double)((getWidth()/2.0)-ypos)/(getWidth()/2.0));

            repaint();
        }
        
        public double round(double preNum, int decPlaces) {
            return (double)Math.round((preNum*Math.pow(10, decPlaces)))/Math.pow(10, decPlaces);
        }

        public void mouseMoved(MouseEvent e) {
            determineMousePos(e);
        }
        
        public void mouseDragged(MouseEvent e) {
            determineMousePos(e);
        }

        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1)
                mouseClicked = !mouseClicked;
            else if (e.getButton() == 3)
                trigger = true;
            repaint();
        }
        
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 3) {
                trigger = false;
                repaint();
            }
        }
        
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        
    }
}
