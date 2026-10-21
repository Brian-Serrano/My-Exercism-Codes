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

class KillerSudokuHelper
{
    public function combinations(int $sum, int $size, array $exclude): array
    {
        $result = [];
        $c = $this->getCombinations(range(1, 9), $size);

        foreach ($c as $i) {
            if (array_sum($i) == $sum && !array_any($exclude, fn($x) => in_array($x, $i))) {
                $result[] = $i;
            }
        }
        return $result;
    }

    function getCombinations(array $arr, int $r): array
    {
        $result = [];

        $backtrack = function(int $start, array $current) use (&$result, $arr, $r, &$backtrack) {
            if (count($current) == $r) {
                $result[] = $current;
                return;
            }
            for ($i = $start; $i < count($arr); $i++) {
                $current[] = $arr[$i];
                $backtrack($i + 1, $current);
                array_pop($current);
            }
        };
        $backtrack(0, []);
        return $result;
    }
}
