using System.Numerics;
using System.Text.RegularExpressions;

public static class IntergalacticTransmission
{
    public static byte[] GetTransmitSequence(byte[] message)
    {
        string binString = string.Join("", message
            .Select(b => Convert.ToString(b, 2).PadLeft(8, '0')));
        List<string> result = Regex.Matches(binString, ".{1,7}")
            .Select(m => m.Value).ToList();
        for (int i = 0; i < result.Count; i++)
        {
            string padded = result[i].PadRight(7, '0');
            padded += padded.Count(x => x == '1') % 2;
            result[i] = padded;
        }
        return result.Select(m => Convert.ToByte(m, 2)).ToArray();
    }

    public static byte[] DecodeSequence(byte[] receivedSeq)
    {
        if (receivedSeq.Any(s => BitOperations.PopCount(s) % 2 != 0))
            throw new ArgumentException();

        string binString = string.Join("", receivedSeq
            .Select(b => Convert.ToString(b, 2).PadLeft(8, '0'))
            .Select(b => b.Substring(0, b.Length - 1)));
        List<string> result = Regex.Matches(binString, ".{8}")
            .Select(m => m.Value).ToList();
        return result.Select(m => Convert.ToByte(m, 2)).ToArray();
    }
}
