package grinea; /*
 * Author: Owen Frere 19520500
 * File: InputFunctions
 * Purppose: Provides generic user input tools
 * Date Modified: 27/10/2018
 *
 * References: These were all submitted for OOPD assignment
 */
import java.util.Scanner;
import java.util.InputMismatchException;
public class InputFunctions
{

 /*
 * getAnyInt
 * I: prompt (String)
 * E: intInput (int)
 * P: get any integer from user
 * W: Uses scanner to handle IO and uses try catch to avoid exceptions
 *      from invalid inputs
 */   
    public static int getAnyInt(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        int intInput = 0;
        boolean valid = false;
        String errMsg = "", trashCan;

        do
        {
            try
            {
                System.out.println(errMsg + prompt);
                intInput = sc.nextInt();
                valid = true;
            }
            catch (InputMismatchException e)
            {
                trashCan = sc.nextLine();
                errMsg = "\nOnly enter whole numbers. eg. -2, 0, or 99.\n";
            }
        } while (!valid);

        return intInput;
    }

 /*
 * getBoundInt
 * I: min (int), max (int), prompt (String)
 * E: intInput
 * P: Get an integer between (inclusive) supplied bounds
 * W: uses getAnyInt to handle the actual integer acquisition
 *      and then checks if out of range and loops the call if so 
 */
    public static int getBoundInt(int min, int max, String prompt)
    {
        int intInput;

        intInput = getAnyInt(prompt);
        while (intInput < min || intInput > max)
        {
            System.out.println("\nMust be between " + min + " and " + max
                + " inclusive.");
            intInput = getAnyInt(prompt);
        }

        return intInput;
    }

/*
 * getAnyDouble
 * I: prompt (String)
 * E: dubInput (double)
 * P: get any double from user
 * W: Uses scanner to handle IO and uses try catch to avoid exceptions
 *      from invalid inputs
 */
    public static double getAnyDouble(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        double dubInput = 0.0;
        boolean valid = false;
        String errMsg = "", trashCan;

        do
        {
            try
            {
                System.out.println(errMsg + prompt);
                dubInput = sc.nextDouble();
                valid = true;
            }
            catch (InputMismatchException e)
            {
                trashCan = sc.nextLine();
                errMsg = "\nOnly enter numbers, eg. -2.3, 0, or 9.9.\n";
            }
        } while (!valid);

        return dubInput;
    }

 /*
 * getBoundDouble
 * I: min (double), max (double), prompt (String)
 * E: dubInput
 * P: Get an double between (inclusive) supplied bounds
 * W: uses getAnyDouble to handle the actual double acquisition
 *      and then checks if out of range and loops the call if so 
 */
    public static double getBoundDouble(double min, double max, String prompt)
    {
        double dubInput;

        dubInput = getAnyDouble(prompt);
        while (dubInput < min || dubInput > max)
        {
            System.out.println("\nMust be between " + min + " and " + max
                + " inclusive.");
            dubInput = getAnyDouble(prompt);
        }

        return dubInput;
    }

 /*
 * getAnyString
 * I: prompt (String)
 * E: strInput (String)
 * P: get a string from the user
 * W: uses scanner to get the string, replaces blank or whitespace
 *      only strings with "NA"
 */
    public static String getString(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        String strInput;

        System.out.println(prompt);
        strInput = sc.nextLine();

        return strInput;
    }
}
