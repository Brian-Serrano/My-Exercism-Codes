using System;
using System.Linq;
public static class RotationalCipher
{
    public static string Rotate(string text, int shiftKey) => string.Join("", 
        text.Select(x => 
            (x >= 'a' && x <= 'z') ? 
            (char)(((x - 'a') + shiftKey) % 26 + 'a') : 
            (x >= 'A' && x <= 'Z') ? 
            (char)(((x - 'A') + shiftKey) % 26 + 'A') : 
            x));
}