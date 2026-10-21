using System.Collections.Generic;

public static class BottleSong
{
    private static string[,] numbers = new string[,] {
        {"One", "no"},
        {"Two", "one"},
        {"Three", "two"},
        {"Four", "three"},
        {"Five", "four"},
        {"Six", "five"},
        {"Seven", "six"},
        {"Eight", "seven"},
        {"Nine", "eight"},
        {"Ten", "nine"}
    };

    public static IEnumerable<string> Recite(int startBottles, int takeDown)
    {
        List<string> result = new List<string>();

        for (int i = startBottles; i > startBottles - takeDown; i--)
        {
            string n = numbers[i - 1, 0];
            string bottle1 = n == "One" ? "bottle" : "bottles";
            result.Add($"{n} green {bottle1} hanging on the wall,");
            result.Add($"{n} green {bottle1} hanging on the wall,");

            result.Add("And if one green bottle should accidentally fall,");

            string s = numbers[i - 1, 1];
            string bottle2 = s == "one" ? "bottle" : "bottles";
            result.Add($"There'll be {s} green {bottle2} hanging on the wall.");

            if (i > startBottles - takeDown + 1)
                result.Add("");
        }

        return result;
    }
}
