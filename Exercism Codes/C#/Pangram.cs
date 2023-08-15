using System;

public static class Pangram
{
    public static bool IsPangram(string input)
    {
        string lowerInput = input.ToLower();
        for(int i = 97; i <= 122; i++) if (!lowerInput.Contains((char)i)) return false;
        return true;
    }
}
