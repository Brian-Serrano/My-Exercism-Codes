using System;
using System.Collections.Generic;

public static class SecretHandshake
{
    public static string[] Commands(int commandValue)
    {
        string binary = Convert.ToString(commandValue, 2);
        List<string> signals = new List<string>();
        for (int i = binary.Length - 1; i >= 0; i--)
        {
            if (Equals(binary[i], '1'))
            {
                switch ((binary.Length - 1) - i)
                {
                    case 0:
                        signals.Add("wink");
                        break;
                    case 1:
                        signals.Add("double blink");
                        break;
                    case 2:
                        signals.Add("close your eyes");
                        break;
                    case 3:
                        signals.Add("jump");
                        break;
                    case 4:
                        signals.Reverse();
                        break;
                }
            }
        }
        return signals.ToArray();
    }
}
