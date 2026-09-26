public static class VariableLengthQuantity
{
    public static uint[] Encode(uint[] numbers)
    {
        List<uint> result = new List<uint>();
        for (int i = 0; i < numbers.Length; i++) {
            if (numbers[i] == 0) {
                result.Add(0);
                continue;
            }
            
            List<uint> res = new List<uint>();
            
            while (numbers[i] > 0) {
                res.Add(numbers[i] & 0x7F);
                numbers[i] >>= 7;
            }
            for (int j = 1; j < res.Count; j++) {
                res[j] |= 0x80;
            }
            res.Reverse();
            result.AddRange(res);
        }
        return result.ToArray();
    }

    public static uint[] Decode(uint[] bytes)
    {
        List<uint> result = new List<uint>();
        int c = 0;
        result.Add(0);
        for (int i = 0; i < bytes.Length; i++) {
            if (bytes.Length - 1 == i && (bytes[i] & 0x80) != 0) {
                throw new InvalidOperationException();
            }
            result[c] = (result[c] << 7) | (bytes[i] & 0x7F);
            if ((bytes[i] & 0x80) == 0 && bytes.Length - 1 != i) {
                result.Add(0);
                c++;
            }
        }
        return result.ToArray();
    }
}