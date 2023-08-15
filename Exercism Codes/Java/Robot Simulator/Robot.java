class Robot {

    private GridPosition gridPosition;
    private Orientation orientation;

    public Robot(GridPosition gp, Orientation o) {
        gridPosition = gp;
        orientation = o;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void turnLeft() {
        orientation = switch(orientation) {
            case NORTH -> Orientation.WEST;
            case EAST -> Orientation.NORTH;
            case SOUTH -> Orientation.EAST;
            case WEST -> Orientation.SOUTH;
        };
    }

    public void turnRight() {
        orientation = switch(orientation) {
            case NORTH -> Orientation.EAST;
            case EAST -> Orientation.SOUTH;
            case SOUTH -> Orientation.WEST;
            case WEST -> Orientation.NORTH;
        };
    }

    public void advance() {
        gridPosition = switch (orientation) {
            case NORTH -> new GridPosition(gridPosition.x, gridPosition.y + 1);
            case EAST -> new GridPosition(gridPosition.x + 1, gridPosition.y);
            case SOUTH -> new GridPosition(gridPosition.x, gridPosition.y - 1);
            case WEST -> new GridPosition(gridPosition.x - 1, gridPosition.y);
        };
    }

    public void simulate(String instruction) {
        for(int i = 0; i < instruction.length(); i++) {
            switch (instruction.charAt(i)) {
                case 'L' -> turnLeft();
                case 'R' -> turnRight();
                case 'A' -> advance();
            }
        }
    }
}
