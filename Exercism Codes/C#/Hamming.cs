using System;
using System.Linq;
public static class Hamming
{
    public static int Distance(string firstStrand, string secondStrand) => firstStrand.Length != secondStrand.Length
        ? throw new ArgumentException()
        : Enumerable.Range(0, firstStrand.Length).Where(x => firstStrand[x] != secondStrand[x]).Count();
}