using System;
using System.Collections.Generic;
using System.Linq;

public static class TelemetryBuffer
{
    public static byte[] ToBuffer(long reading)
    {
        IEnumerable<byte> bytes = reading switch
        {
            < int.MinValue => BitConverter.GetBytes(reading).Prepend((byte)248),
            < short.MinValue => BitConverter.GetBytes((int)reading).Prepend((byte)252),
            < ushort.MinValue => BitConverter.GetBytes((short)reading).Prepend((byte)254),
            <= ushort.MaxValue => BitConverter.GetBytes((ushort)reading).Prepend((byte)2),
            <= int.MaxValue => BitConverter.GetBytes((int)reading).Prepend((byte)252),
            <= uint.MaxValue => BitConverter.GetBytes((uint)reading).Prepend((byte)4),
            _ => BitConverter.GetBytes(reading).Prepend((byte)248),
        };
        return bytes.Concat(new byte[9 - bytes.Count()]).ToArray();
    }

    public static long FromBuffer(byte[] buffer)
    {
        return buffer[0] switch
        {
            248 or 4 or 2 => BitConverter.ToInt64(buffer, 1),
            252 => BitConverter.ToInt32(buffer, 1),
            254 => BitConverter.ToInt16(buffer, 1),
            _ => 0,
        };
    }
}
