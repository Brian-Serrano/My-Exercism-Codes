import java.util.*;

public class MazeGenerator {

    public char[][] generatePerfectMaze(int rows, int columns) {
        if (rows < 5 || rows > 100 || columns < 5 || columns > 100) {
            throw new IllegalArgumentException();
        }
        return generateMaze(rows, columns, new Random());
    }

    public char[][] generatePerfectMaze(int rows, int columns, int seed) {
        if (rows < 5 || rows > 100 || columns < 5 || columns > 100) {
            throw new IllegalArgumentException();
        }
        return generateMaze(rows, columns, new Random(seed));
    }

    private char[][] generateMaze(int rows, int columns, Random random) {
        int xLength = columns * 2 + 1;
        int yLength = rows * 2 + 1;
        char[][] maze = new char[yLength][xLength];
        int randomStart = 2 * random.nextInt(0, yLength / 2) + 1;
        int randomEnd = 2 * random.nextInt(0, yLength / 2) + 1;

        maze[randomStart][0] = '⇨';
        maze[randomEnd][xLength - 1] = '⇨';

        Offset offset = new Offset(1, randomStart, 0);
        maze[offset.y][offset.x] = ' ';

        ArrayList<Integer> rotations = new ArrayList<>(List.of(-1, 0, 1));
        int rotation = random.nextInt(-1, 2);
        createPath(maze, random, offset, xLength, yLength, rotation, rotations);

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (maze[j][i] != ' ' && maze[j][i] != '⇨') {
                    boolean a = j - 1 < 0; // up
                    boolean b = j + 1 >=  yLength; // down
                    boolean c = i - 1 < 0; // left
                    boolean d = i + 1 >= xLength; // right
                    boolean e = a || maze[j - 1][i] == ' ';
                    boolean f = b || maze[j + 1][i] == ' ';
                    boolean g = c || maze[j][i - 1] == ' ';
                    boolean h = d || maze[j][i + 1] == ' ';
                    boolean k = !a && maze[j - 1][i] != ' ';
                    boolean l = !b && maze[j + 1][i] != ' ';
                    boolean m = !c && maze[j][i - 1] != ' ';
                    boolean n = !d && maze[j][i + 1] != ' ';
                    if (e && f) {
                        maze[j][i] = '─';
                    }
                    else if (g && h) {
                        maze[j][i] = '│';
                    }
                    else if (e && l && g && n) {
                        maze[j][i] = '┌';
                    }
                    else if (e && l && m && h) {
                        maze[j][i] = '┐';
                    }
                    else if (k && f && g && n) {
                        maze[j][i] = '└';
                    }
                    else if (k && f && m && h) {
                        maze[j][i] = '┘';
                    }
                    else if (e && l && m && n) {
                        maze[j][i] = '┬';
                    }
                    else if (k && f && m && n) {
                        maze[j][i] = '┴';
                    }
                    else if (k && l && g && n) {
                        maze[j][i] = '├';
                    }
                    else if (k && l && m && h) {
                        maze[j][i] = '┤';
                    }
                    else if (k && l && m && n) {
                        maze[j][i] = '┼';
                    }
                }
            }
        }

        return maze;
    }

    private void createPath(char[][] maze, Random random, Offset offset, int xLength,
                            int yLength, int rotation, ArrayList<Integer> rotations) {
        // 0 right, 1 down, 2 left, 3 up
        int newDirection = Math.floorMod(offset.direction + rotation, 4);

        switch (newDirection) {
            case 0:
                if (offset.x + 2 >= xLength || maze[offset.y][offset.x + 2] == ' ') {
                    rotations.remove(Integer.valueOf(rotation));
                    if (rotations.isEmpty()) {
                        return;
                    }
                    rotation = rotations.get(random.nextInt(0, rotations.size()));
                    createPath(maze, random, offset, xLength, yLength, rotation, rotations);
                }
                else {
                    maze[offset.y][offset.x + 1] = ' ';
                    maze[offset.y][offset.x + 2] = ' ';
                    Offset newOffset = new Offset(offset.x + 2, offset.y, newDirection);
                    splitDirections(maze, random, newOffset, xLength, yLength);
                }
            case 1:
                if (offset.y + 2 >= yLength || maze[offset.y + 2][offset.x] == ' ') {
                    rotations.remove(Integer.valueOf(rotation));
                    if (rotations.isEmpty()) {
                        return;
                    }
                    rotation = rotations.get(random.nextInt(0, rotations.size()));
                    createPath(maze, random, offset, xLength, yLength, rotation, rotations);
                }
                else {
                    maze[offset.y + 1][offset.x] = ' ';
                    maze[offset.y + 2][offset.x] = ' ';
                    Offset newOffset = new Offset(offset.x, offset.y + 2, newDirection);
                    splitDirections(maze, random, newOffset, xLength, yLength);
                }
            case 2:
                if (offset.x - 2 < 0 || maze[offset.y][offset.x - 2] == ' ') {
                    rotations.remove(Integer.valueOf(rotation));
                    if (rotations.isEmpty()) {
                        return;
                    }
                    rotation = rotations.get(random.nextInt(0, rotations.size()));
                    createPath(maze, random, offset, xLength, yLength, rotation, rotations);
                }
                else {
                    maze[offset.y][offset.x - 1] = ' ';
                    maze[offset.y][offset.x - 2] = ' ';
                    Offset newOffset = new Offset(offset.x - 2, offset.y, newDirection);
                    splitDirections(maze, random, newOffset, xLength, yLength);
                }
            default:
                if (offset.y - 2 < 0 || maze[offset.y - 2][offset.x] == ' ') {
                    rotations.remove(Integer.valueOf(rotation));
                    if (rotations.isEmpty()) {
                        return;
                    }
                    rotation = rotations.get(random.nextInt(0, rotations.size()));
                    createPath(maze, random, offset, xLength, yLength, rotation, rotations);
                }
                else {
                    maze[offset.y - 1][offset.x] = ' ';
                    maze[offset.y - 2][offset.x] = ' ';
                    Offset newOffset = new Offset(offset.x, offset.y - 2, newDirection);
                    splitDirections(maze, random, newOffset, xLength, yLength);
                }
        }
    }

    private void splitDirections(char[][] maze, Random random, Offset offset,
                                 int xLength, int yLength) {
        List<Integer> numbers = new ArrayList<>(List.of(-1, 0, 1));
        Collections.shuffle(numbers, random);

        for (int i = 0; i <= 2; i++) {
            ArrayList<Integer> rotations = new ArrayList<>(List.of(-1, 0, 1));
            createPath(maze, random, offset, xLength, yLength, numbers.get(i), rotations);
        }
    }

    static class Offset {

        public int x, y, direction;

        public Offset(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}