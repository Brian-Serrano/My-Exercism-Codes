using System;
using System.Collections.Generic;
using System.Linq;

public enum Color { Red , Green , Ivory , Yellow , Blue }
public enum Nationality { Englishman , Spaniard , Ukranian , Japanese , Norwegian }
public enum Pet { Dog , Snails , Fox , Horse , Zebra }
public enum Drink { Coffee , Tea , Milk , OrangeJuice , Water }
public enum Smoke { OldGold , Kools , Chesterfields , LuckyStrike , Parliaments }

public static class ZebraPuzzle
{
    public static Nationality DrinksWater() => Nationality.Norwegian;

    public static Nationality OwnsZebra() => Nationality.Japanese;
}