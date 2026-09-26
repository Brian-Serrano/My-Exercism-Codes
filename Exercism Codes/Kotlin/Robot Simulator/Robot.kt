class Robot(
    var gridPosition: GridPosition = GridPosition(0, 0),
    var orientation: Orientation = Orientation.NORTH
) {

    fun simulate(instructions: String) {
        instructions.forEach {
            when (it) {
                'L' -> orientation = Orientation.values()[(orientation.ordinal + 3) % 4]
                'R' -> orientation = Orientation.values()[(orientation.ordinal + 1) % 4]
                'A' -> gridPosition = when (orientation) {
                    Orientation.NORTH -> GridPosition(gridPosition.x, gridPosition.y + 1)
                    Orientation.EAST -> GridPosition(gridPosition.x + 1, gridPosition.y)
                    Orientation.SOUTH -> GridPosition(gridPosition.x, gridPosition.y - 1)
                    Orientation.WEST -> GridPosition(gridPosition.x - 1, gridPosition.y)
                }
            }
        }
    }
}
