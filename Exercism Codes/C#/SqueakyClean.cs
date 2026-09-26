using System;
using System.Text;

public static class Identifier
{
    public static string Clean(string identifier)
    {
        char[] si = identifier.ToCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < si.Length; i++)
        {
            if (char.IsControl(si[i])) sb.Append("CTRL");
            if (si[i] == 32) sb.Append('_');
            if (char.IsLetter(si[i]) && !(si[i] >= '\u03B1' && si[i] <= '\u03C9')) sb.Append(si[i]);
            if (si[i] == 45) sb.Append(char.ToUpper(si[++i]));
        }

        return sb.ToString();
    }
}
