public class SimpleCipher
{
    public SimpleCipher()
    {
        Key = string.Join("", Enumerable.Range(0, 100).Select(x => (char)(new Random().Next(26) + 97)));
    }

    public SimpleCipher(string key) => Key = key;

    public string Key { get; set; }

    public string Encode(string plaintext) => string.Join("", plaintext.Select((x, idx) => (char)(97 + (((x - 97) + (Key[idx % Key.Length] - 97)) % 26))));

    public string Decode(string ciphertext)
    {
        return string.Join("", ciphertext.Select((x, idx) =>
        {
            int result = ((x - 97) - (Key[idx % Key.Length] - 97)) % 26;
            return (char)(97 + (result < 0 ? result + 26 : result));
        }));
    }
}