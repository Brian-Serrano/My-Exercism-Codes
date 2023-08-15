using System;
using System.Collections.Generic;
using System.Linq;

public static class Diamond
{
    public static string Make(char target)
    {
        int size = 2 * (target - 'A') + 1;
        string[] lines = new string[size];
        for(int i = 0; i < size / 2 + 1; i++)
        {
            char letter = (char)('A' + i);
            List<char> buffer = Enumerable.Repeat(' ', size).ToList();
            int leftPosition = size / 2 + 1 - i - 1;
            buffer[leftPosition] = letter;
            buffer[size - leftPosition - 1] = letter;
            string line = string.Join("", buffer);
            lines[i] = line;
            lines[size - i - 1] = line;
        }
        return string.Join("\n", lines);
    }
}