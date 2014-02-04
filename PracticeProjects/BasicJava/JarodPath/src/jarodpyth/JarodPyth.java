/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jarodpyth;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * this program cals the pythga theory w/ user input
 */

import java.util.Scanner;

/**
 *
 * @author bert2
 */
public class JarodPyth {

    /**
     * @param args the command line arguments
     */
    
  
    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
        // TODO code application logic here
           //Math.sqrt(10);
       double A, B, C, answer;
       String i;
       
       System.out.println("What value are you solving for? (a,b,c)");
       i = input.nextLine();
       if (i.equals("a")){
           System.out.println("What is the value of c?");
          C = input.nextDouble();
          System.out.println("What is the value of b?");
          B = input.nextDouble();
          answer = C * C - B * B;
          answer = Math.sqrt(answer);
          System.out.println("The value of a is " + answer);        
          
          }
       
       else if (i.equals("b")){
           System.out.println("What is the value of c?");
           C = input.nextDouble();
           System.out.println("What is the value of a?");
           A = input.nextDouble();
           answer = C * C - A * A;
           answer = Math.sqrt(answer);
           System.out.println("The value of b is " + answer);
           
           }
       
       else if (i.equals("c")){
           System.out.println("What is the value of a?");
           A = input.nextDouble();
           System.out.println("What is the value of b?");
           B = input.nextDouble();
           answer = A * A + B * B;
           answer = Math.sqrt(answer);
           System.out.println("The value of c is " + answer);
           
           }
       
       else{
           System.out.println("invalid variable");
       }
     
    }   
}