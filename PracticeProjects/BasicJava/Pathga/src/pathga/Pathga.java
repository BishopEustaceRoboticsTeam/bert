package pathga;

import java.math.*;
import java.util.Scanner;

/**
 *
 * @author bert2
 */
public class Pathga {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a, b, c;
        String variable;
        char[] aborc;
        System.out.println("What variable do you want to solve for?/n     (a, b, c)");
        variable = input.next();
        variable = variable.toLowerCase();
        variable = variable.trim();
        aborc = variable.toCharArray();

        if (aborc[0] == 'a') {
            //Solving for a. a=sqrt(c^2-b^2)
            System.out.println("What is the value of b?");
            b = input.nextDouble();
            System.out.println("What is the value of c?");
            c = input.nextDouble();
            a = c * c - b * b;
            a = Math.sqrt(a);
            System.out.println("The value of side a is " + a);

        } else if (aborc[0] == 'b') {
            //Solving for b. b=sqrt(c^2-a^2)
            System.out.println("What is the value of a?");
            a = input.nextDouble();
            System.out.println("What is the value of c?");
            c = input.nextDouble();
            b = c * c - a * a;
            b = Math.sqrt(b);
            System.out.println("The value of side b is " + b);
        } else if (aborc[0] == 'c') {
            //Solving for c. a=sqrt(a^2+b^2)
            System.out.println("What is the value of a?");
            a = input.nextDouble();
            System.out.println("What is the value of b?");
            b = input.nextDouble();
            c = a * a + b * b;
            c = Math.sqrt(c);
            System.out.println("The value of side c is " + c);
        } else {
            System.out.println("INVALID SELECTION.\nTERMINATING USER...");
            System.out.println("You entered: " + variable);
        }
    }
}
