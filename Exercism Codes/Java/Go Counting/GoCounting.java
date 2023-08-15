import java.awt.*;
import java.util.*;
import java.util.List;

class GoCounting {

    private final String[] board;

    public GoCounting(String board) {
        this.board = board.split("\n");
    }

    public Player getTerritoryOwner(int x, int y) {
        Set<Point> territory = getTerritory(x, y);
        List<Character> playersSurrounding = new ArrayList<>();

        for(Point p : territory) {
            Point[] offsets = new Point[] {
                    new Point(p.x + 1, p.y),
                    new Point(p.x - 1, p.y),
                    new Point(p.x, p.y + 1),
                    new Point(p.x, p.y - 1)
            };
            for(Point o : offsets) {
                if(checkBounds(o) && (board[o.y].charAt(o.x) == 'B' || board[o.y].charAt(o.x) == 'W')) {
                    playersSurrounding.add(board[o.y].charAt(o.x));
                }
            }
        }

        return contains(playersSurrounding, 'W', 'B') ? Player.BLACK : contains(playersSurrounding, 'B', 'W') ? Player.WHITE : Player.NONE;
    }

    public Set<Point> getTerritory(int x, int y) {
        if(!checkBounds(new Point(x, y))) throw new IllegalArgumentException("Invalid coordinate");
        return board[y].charAt(x) == ' ' ? getAdjacentPoints(new HashSet<>(), new Point(x, y)) : new HashSet<>();
    }

    public HashMap<Player, Set<Point>> getTerritories() {
        HashMap<Player, Set<Point>> territories = new HashMap<>(Map.of(Player.WHITE, new HashSet<>(), Player.BLACK, new HashSet<>(), Player.NONE, new HashSet<>()));
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length(); j++) {
                territories.get(getTerritoryOwner(j, i)).addAll(getTerritory(j, i));
            }
        }
        return territories;
    }

    private Set<Point> getAdjacentPoints(Set<Point> points, Point target) {
        Point[] offsets = new Point[] {
                new Point(target.x + 1, target.y),
                new Point(target.x - 1, target.y),
                new Point(target.x, target.y + 1),
                new Point(target.x, target.y - 1)
        };
        points.add(target);
        for(Point p : offsets) {
            if(checkBounds(p) && board[p.y].charAt(p.x) == ' ' && !points.contains(p)) {
                getAdjacentPoints(points, p);
            }
        }
        return points;
    }

    private boolean checkBounds(Point point) {
        return point.x >= 0 && point.x < board[0].length() && point.y >= 0 && point.y < board.length;
    }

    private boolean contains(List<Character> p, char nc, char c) {
        return !p.contains(nc) && p.contains(c);
    }
}
