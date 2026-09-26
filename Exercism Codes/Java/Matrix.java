class Matrix {
    private String matrixAsString;
    Matrix(String matrixAsString) {
        this.matrixAsString = matrixAsString;
    }

    int[] getRow(int rowNumber) {
        String[] rows = matrixAsString.split("\n");
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] rowElements = rows[i].split(" ");
            matrix[i] = new int[rowElements.length];
            for (int j = 0; j < rowElements.length; j++) {
                matrix[i][j] = Integer.parseInt(rowElements[j]);
            }
        }
        return matrix[rowNumber-1];
    }

    int[] getColumn(int columnNumber) {
        String[] rows = matrixAsString.split("\n");
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] rowElements = rows[i].split(" ");
            matrix[i] = new int[rowElements.length];
            for (int j = 0; j < rowElements.length; j++) {
                matrix[i][j] = Integer.parseInt(rowElements[j]);
            }
        }
        int[] column = new int[matrix.length];
        for(int j=0; j<matrix.length; j++){
            column[j] = matrix[j][columnNumber-1];
        }
        return column;
    }
}