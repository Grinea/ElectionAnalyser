package grinea;

/*
 * Author: Owen Frere 19520500
 * File: Tools
 * Purppose: Provides misc tools to ElectionAnalyser
 * Date Modified: 27/10/2018
 */
public class Tools
{
    public static int stateToIdx(String state)
    {
        int idx = 0;

        if (state.equals("ACT"))
        {
            idx = 0;
        }
        else if (state.equals("NSW"))
        {
            idx = 1;
        }
        else if (state.equals("NT"))
        {
            idx = 2;
        }
        else if (state.equals("QLD"))
        {
            idx = 3;
        }
        else if (state.equals("SA"))
        {
            idx = 4;
        }
        else if (state.equals("TAS"))
        {
            idx = 5;
        }
        else if (state.equals("VIC"))
        {
            idx = 6;
        }
        else if (state.equals("WA"))
        {
            idx = 7;
        }

        return idx;
    }

    public static String idxToState(int idx)
    {
        String state;

        switch (idx)
        {
            case 0:
                state = "ACT";
                break;
            case 1:
                state = "NSW";
                break;
            case 2:
                state = "NT";
                break;
            case 3:
                state = "QLD";
                break;
            case 4:
                state = "SA";
                break;
            case 5:
                state = "TAS";
                break;
            case 6:
                state = "VIC";
                break;
            case 7:
                state = "WA";
                break;
            default:
                state = "err";

        }

        return state;
    }
}
