using System;
using System.IO;
using System.Linq;
using System.Text;
using System.Collections.Generic;

public static class Tournament
{
    public class Player
    {
        public string Name { get; set; }
        public int MatchesPlayed { get; set; }
        public int Wins { get; set; }
        public int Draws { get; set; }
        public int Losses { get; set; }
        public int Points { get; set; }
    }

    public static void Tally(Stream inStream, Stream outStream)
    {
        var playerDict = new Dictionary<string, Player>();
        using (var reader = new StreamReader(inStream))
        {
            var lines = reader.ReadToEnd().Split("\n", StringSplitOptions.RemoveEmptyEntries);
            foreach (var line in lines)
            {
                var parts = line.Split(";");
                var player1 = GetOrCreatePlayer(playerDict, parts[0]);
                var player2 = GetOrCreatePlayer(playerDict, parts[1]);
                ProcessResult(player1, player2, parts[2]);
            }
        }

        using (var writer = new StreamWriter(outStream))
        {
            StringBuilder sb = new StringBuilder("Team".PadRight(30) + " | MP |  W |  D |  L |  P");
            foreach (var player in playerDict.Values.OrderByDescending(p => p.Points).ThenBy(p => p.Name))
            {
                sb.Append($"\n{player.Name.PadRight(30)} | {player.MatchesPlayed,2} | {player.Wins,2} | {player.Draws,2} | {player.Losses,2} | {player.Points,2}");
            }
            writer.Write(sb);
        }
    }

    private static Player GetOrCreatePlayer(Dictionary<string, Player> playerDict, string name)
    {
        if (!playerDict.ContainsKey(name))
            playerDict[name] = new Player { Name = name };
        return playerDict[name];
    }

    private static void ProcessResult(Player player1, Player player2, string result)
    {
        player1.MatchesPlayed++;
        player2.MatchesPlayed++;
        switch (result)
        {
            case "win":
                player1.Wins++; player1.Points += 3;
                player2.Losses++;
                break;
            case "loss":
                player2.Wins++; player2.Points += 3;
                player1.Losses++;
                break;
            case "draw":
                player1.Draws++; player2.Draws++;
                player1.Points++; player2.Points++;
                break;
        }
    }
}