class GameOfLife {
    public int[][] tick(int[][] matrix) {
        if (matrix.length > 0) {
            int[][] newMatrix = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    int aliveCells = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (i + k >= 0 && i + k < matrix.length && j + l >= 0 && j + l < matrix[i].length) {
                                if (matrix[i + k][j + l] == 1 && !(k == 0 && l == 0)) {
                                    aliveCells++;
                                }
                            }
                        }
                    }
                    if (matrix[i][j] == 1) {
                        if (aliveCells == 2 || aliveCells == 3) newMatrix[i][j] = 1;
                        else newMatrix[i][j] = 0;
                    } else {
                        if (aliveCells == 3) newMatrix[i][j] = 1;
                        else newMatrix[i][j] = 0;
                    }
                }
            }
            return newMatrix;
        }
        return new int[][] {};
    }
}
