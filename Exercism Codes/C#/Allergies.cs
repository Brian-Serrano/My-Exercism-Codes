using System;
using System.Collections.Generic;

[Flags]
public enum Allergen
{
    Eggs = 1,
    Peanuts = 2,
    Shellfish = 4,
    Strawberries = 8,
    Tomatoes = 16,
    Chocolate = 32,
    Pollen = 64,
    Cats = 128
}

public class Allergies
{
    private int mask;
    public Allergies(int mask) => this.mask = mask % 256;
    public bool IsAllergicTo(Allergen allergen) => (mask & (int)allergen) != 0;
    
    public Allergen[] List()
    {
        List<Allergen> allergens = new List<Allergen>();
        for(int i = 0; mask != 0; i++)
        {
            if((mask & 1) != 0)
            {
                allergens.Add((Allergen)(1 << i));
            }
            mask >>= 1;
        }
        return allergens.ToArray();
    }
}