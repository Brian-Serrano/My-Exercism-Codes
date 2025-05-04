using System;

public class SpiralMatrix
{
    public static int[,] GetMatrix(int size)
    {
        int[,] matrix = new int[size, size];
        int verIdx = 0, horIdx = 0, direction = 0;
        if (matrix.Length > 0) matrix[0, 0] = 1;
        for (int i = 1; i < matrix.Length; i++) {
            switch (direction) {
                case 0:
                    if (matrix.GetLength(1) > horIdx + 1 && matrix[verIdx, horIdx + 1] == 0) {
                        horIdx++;
                    }
                    else {
                        verIdx++;
                        direction = 1;
                    }
                    break;
                case 1:
                    if (matrix.GetLength(0) > verIdx + 1 && matrix[verIdx + 1, horIdx] == 0) {
                        verIdx++;
                    }
                    else {
                        horIdx--;
                        direction = 2;
                    }
                    break;
                case 2:
                    if (horIdx - 1 >= 0 && matrix[verIdx, horIdx - 1] == 0) {
                        horIdx--;
                    }
                    else {
                        verIdx--;
                        direction = 3;
                    }
                    break;
                case 3:
                    if (verIdx - 1 >= 0 && matrix[verIdx - 1, horIdx] == 0) {
                        verIdx--;
                    }
                    else {
                        horIdx++;
                        direction = 0;
                    }
                    break;
            }
            matrix[verIdx, horIdx] = i + 1;
        }
        return matrix;
    }
}