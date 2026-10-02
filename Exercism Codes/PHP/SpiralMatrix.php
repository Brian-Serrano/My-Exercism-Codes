<?php

/*
 * By adding type hints and enabling strict type checking, code can become
 * easier to read, self-documenting and reduce the number of potential bugs.
 * By default, type declarations are non-strict, which means they will attempt
 * to change the original type to match the type specified by the
 * type-declaration.
 *
 * In other words, if you pass a string to a function requiring a float,
 * it will attempt to convert the string value to a float.
 *
 * To enable strict mode, a single declare directive must be placed at the top
 * of the file.
 * This means that the strictness of typing is configured on a per-file basis.
 * This directive not only affects the type declarations of parameters, but also
 * a function's return type.
 *
 * For more info review the Concept on strict type checking in the PHP track
 * <link>.
 *
 * To disable strict typing, comment out the directive below.
 */

declare(strict_types=1);

class SpiralMatrix
{
    public function draw(int $n): array
    {
        $matrix = array_fill(0, $n, array_fill(0, $n, 0));
        $verIdx = 0;
        $horIdx = 0;
        $direction = 0;
        if ($n * $n > 0)
            $matrix[0][0] = 1;
        for ($i = 1; $i < $n * $n; $i++) {
            switch ($direction) {
                case 0:
                if (count($matrix[0]) > $horIdx + 1 && $matrix[$verIdx][$horIdx + 1] == 0) {
                    $horIdx++;
                }
                else {
                    $verIdx++;
                    $direction = 1;
                }
                break;
                case 1:
                if (count($matrix) > $verIdx + 1 && $matrix[$verIdx + 1][$horIdx] == 0) {
                    $verIdx++;
                }
                else {
                    $horIdx--;
                    $direction = 2;
                }
                break;
                case 2:
                if ($horIdx - 1 >= 0 && $matrix[$verIdx][$horIdx - 1] == 0) {
                    $horIdx--;
                }
                else {
                    $verIdx--;
                    $direction = 3;
                }
                break;
                case 3:
                if ($verIdx - 1 >= 0 && $matrix[$verIdx - 1][$horIdx] == 0) {
                    $verIdx--;
                }
                else {
                    $horIdx++;
                    $direction = 0;
                }
                break;
            }
            $matrix[$verIdx][$horIdx] = $i + 1;
        }
        return $matrix;
    }
}
