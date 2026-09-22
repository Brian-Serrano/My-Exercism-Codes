using System;
using System.Globalization;

public static class HighSchoolSweethearts
{
    public static string DisplaySingleLine(string studentA, string studentB)
    {
        return $"{studentA,29} ♡ {studentB,-29}";
    }

    public static string DisplayBanner(string studentA, string studentB)
    {
        return $@"
     ******       ******
   **      **   **      **
 **         ** **         **
**            *            **
**                         **
**     {studentA.Trim(),-6} + {studentB.Trim(),6}     **
 **                       **
   **                   **
     **               **
       **           **
         **       **
           **   **
             ***
              *
";
        
    }

    public static string DisplayGermanExchangeStudents(string studentA
        , string studentB, DateTime start, float hours)
    {
        return $"{studentA} and {studentB} have been dating since {start:dd.MM.yyyy} - that's {hours.ToString("C", CultureInfo.CreateSpecificCulture("de")).Split(" ")[0]} hours";
    }
}
