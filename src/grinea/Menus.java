package grinea; /*
 * Author: Owen Frere 19520500
 * File: Menus.java
 * Purpose: Menu system for the DSA assignment
 * Date Modified: 27/10/2018
 * Reference Adapted from Menus.java submitted for OOPD assignment
 */

import java.util.Scanner;
import java.io.IOException;
public class Menus
{
 
 /*
 * mainMenu
 * I: None
 * E: int
 * P: Main menu and flow control
 */
    public static int mainMenu()
    {
        String[] menuOptions = new String[] {"\nMain Menu", 
            "List Nominees", "Nominee Search", "List by Margin", 
            "Itinerary by Margin", "Exit"};

        return printMenu(menuOptions);
    }

/*
 * stateMenu
 * I: None
 * E: int
 * P: Provides menu to select a state
 */
    public static int stateMenu()
    {
        String[] menuOptions = new String[] {"\nState to filter by?", "ACT", 
            "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA", "None"};

        return printMenu(menuOptions);
    }

/*
 * sortMenu
 * I: filterOption (String)
 * E: int
 * P: provides menu to chose to sort by something
*/    
    public static int yesNoMenu(String filterOption)
    {
        String[] menuOptions = new String[] {"\n" + filterOption + "?", 
            "Yes", "No"};

        return printMenu(menuOptions);
    }

 /*
 * printMenu
 * I: menuOptions (String[])
 * E: menuReturn (int)
 * P: Prints out a formatted menu and returns a selection from user 
 */
    public static int printMenu(String[] menuOptions)
    {
        int menuReturn;

        //clearTerminal();

        System.out.println(menuOptions[0]);

        for (int ii = 1; ii < menuOptions.length; ii++)
        {
            System.out.println(ii + ". " + menuOptions[ii]);
        }

        menuReturn = InputFunctions.getBoundInt(1, menuOptions.length - 1,
            "\nSelect menu option number:");

        //clearTerminal();

        return menuReturn;
    }
}
