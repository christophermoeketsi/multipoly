/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.multipoly.commonutilties;

public class StringUtils
{
    public static Integer ContainsString(String str, String subString, String delimiter)
    {
        String [] colList    = str.split(delimiter);
        for (int i = 0; i < colList.length; i++)
        {
            if (colList[i].equals(subString))
                return i;
        }
        return -1;
    }

    public static String ReformatString(String oldString, String oldDelimiter, String newDelimiter, Boolean bQuotes)
    {
        int i = 0;
        String newString = new String(); 
        String [] splitHeader = oldString.split(oldDelimiter);
        for (String str : splitHeader)
        {
            if (bQuotes)
                newString += "\"" + str + "\"";
            else
                newString += str ;
            if (i != splitHeader.length-1)
                newString += newDelimiter;
            i++;
        }
        return newString;
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isEmpty(String property) {
        return false;
    }
}
