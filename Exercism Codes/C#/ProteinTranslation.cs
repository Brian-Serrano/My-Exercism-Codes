using System;
using System.Collections.Generic;

public static class ProteinTranslation
{
    public static string[] Proteins(string strand)
    {
        List<string> lst = new List<string>();

        for(int i = 0; i < strand.Length; i += 3)
        {
            string s = strand.Substring(i, 3);
            switch(s)
            {
                case "AUG":
                    lst.Add("Methionine");
                    break;
                case "UUU":
                case "UUC":
                    lst.Add("Phenylalanine");
                    break;
                case "UUA":
                case "UUG":
                    lst.Add("Leucine");
                    break;
                case "UCU":
                case "UCC":
                case "UCA":
                case "UCG":
                    lst.Add("Serine");
                    break;
                case "UAU":
                case "UAC":
                    lst.Add("Tyrosine");
                    break;
                case "UGU":
                case "UGC":
                    lst.Add("Cysteine");
                    break;
                case "UGG":
                    lst.Add("Tryptophan");
                    break;
                case "UAA":
                case "UAG":
                case "UGA":
                    return lst.ToArray();
            }
        }

        return lst.ToArray();
    }
}