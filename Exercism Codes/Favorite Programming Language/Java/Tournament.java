import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Tournament {

    private List<Player> playerData = new ArrayList<>();

    String printTable() {
        StringBuilder lines = new StringBuilder("Team                           | MP |  W |  D |  L |  P\n");
        for(Player p : playerData) {
            lines.append(String.format("%s%s|  %d |  %d |  %d |  %d |  %d\n", p.name, " ".repeat(31 - p.name.length()), p.played, p.win, p.draw, p.loss, p.points));
        }
        return lines.toString();
    }

    void applyResults(String resultString) {
        List<String[]> data = Arrays.stream(resultString.split("\n")).map(s -> s.split(";")).toList();
        List<Player> players = new ArrayList<>();
        for(String[] d : data) {
            for(int i = 0; i < 2; i++) {
                String status = i == 0 ? d[2] : reverse(d[2]);
                int playerIdx = players.stream().map(p -> p.name).toList().indexOf(d[i]);
                if(playerIdx < 0) players.add(new Player(d[i], status));
                else players.get(playerIdx).update(status);
            }
        }
        playerData = players.stream().sorted((a, b) -> a.points == b.points ? a.name.compareTo(b.name) : b.points - a.points).toList();
    }

    public String reverse(String status) {
        return status.equals("draw") ? "draw" : (status.equals("win") ? "loss" : "win");
    }

    static class Player {
        public String name;
        public int played, win, loss, draw, points;

        Player(String name, String value) {
            this.name = name;
            win = 0;
            loss = 0;
            draw = 0;
            update(value);
        }

        void update(String value) {
            if(value.equals("win")) win++;
            if(value.equals("draw")) draw++;
            if(value.equals("loss")) loss++;
            played = win + loss + draw;
            points = win * 3 + draw;
        }
    }
}
