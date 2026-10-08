public static class RnaTranscription
{
    public static string ToRna(string strand)
    {
        Dictionary<char, char> complement = new Dictionary<char, char>
        {
            { 'G', 'C' },
            { 'C', 'G' },
            { 'T', 'A' },
            { 'A', 'U' }
        };
        return new string(strand.Select(nucleotide => complement[nucleotide]).ToArray());
    }
}