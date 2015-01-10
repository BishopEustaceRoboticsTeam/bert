package zerocalc;

import java.util.*;
import java.math.*;


/**
 *
 * @author bert2
 */
public class ZeroCalc {

    
    public static double evalF(ArrayList coeffsF, double x) {
        double y = 0;
        for (int f = 0; f < coeffsF.size(); f++) {
            y += (double)(coeffsF.get(f)) * Math.pow(x,f);
        }
        return y;
    }
    
    public static double evalFP(ArrayList coeffsFP, double x) {    
        double m = 0;
        for (int f = 0; f < coeffsFP.size(); f++) {
            m += (double)(coeffsFP.get(f)) * Math.pow(x,f) * (f + 1);
        }
        return m;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double x = 0;
        double y = 1,m = 1;
        int degree = 0;
        double[] coef = new double[6];
        ArrayList coeffsF = new ArrayList();
        ArrayList coeffsFP = new ArrayList();
        
        
        while (degree == 0) {
            System.out.println("What's the degree of the function?");
            degree = input.nextInt();
            if (degree < 1 || degree > 5) {
                System.out.println("Invalid value");
                degree = 0;
            }
        }
        for (int f = 0; f <= degree; f++) {
            if (f == 0) {
                System.out.println("What's the value of the constant?");
            } else if (f == 1) {
                System.out.println("What's the coefficient of x");
            } else {
                System.out.println("What's the coefficient of x^" + f);
            }
            coeffsF.add(f, input.nextDouble());
            if (f >= 1) {
                coeffsFP.add(f - 1, coeffsF.get(f));
            }
        }
        System.out.println("Calculating zero...");
        
        
        while (Math.abs(y) > .000001) {
            x = -y / m;
            y = evalF(coeffsF, x);
            m = evalFP(coeffsFP, x);
            if (m == 0) {
                m = 1;
            }
            System.out.println("x = " + x + "\ny = " + y + "\nm = " + m + "\n");
        }
        
        System.out.println("Zero of function is located at x=" + x);
    }
}