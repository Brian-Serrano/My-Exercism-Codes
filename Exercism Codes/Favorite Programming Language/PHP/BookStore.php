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

/**
 * Note: we expect the total in cents (1$ = 100 cents).
 */
function total(array $items): int
{
    $cnt = getFrequency($items);
    $freq = !empty(array_values($cnt)) ? max(array_values($cnt)) : 0;
    $combinations = findCombinations($cnt, $freq, array_keys($cnt), 0, [array_fill(0, $freq, 0)]);
    $discounts = [1 => 0.0, 2 => 0.4, 3 => 0.8, 4 => 1.6, 5 => 2.0];

    return min(array_map(function($lst) use ($discounts) {
        return array_sum(array_map(function($b) use ($discounts) {
            return intval(100 * $b * (8.0 - $discounts[$b]));
        }, $lst));
    }, $combinations));
}

function getFrequency(array $books): array
{
    $frequency = [];
    foreach ($books as $x) {
        if (array_key_exists($x, $frequency)) {
            $frequency[$x] += 1;
        }
        else {
            $frequency[$x] = 1;
        }
    }
    return $frequency;
}

function findCombinations(array $cnt, int $freq, array $nums, int $num, array $arr) 
{
    if ($num == count($nums)) {
        return $arr;
    }
    else {
        $arr2 = $arr;
        $arr = [];

        $positionsList = getCombinations(range(0, $freq - 1), $cnt[$nums[$num]]);
        foreach ($arr2 as $x) {
            foreach ($positionsList as $positions) {
                $newArr = $x;
                foreach ($positions as $j) {
                    $newArr[$j] += 1;
                }
                $arr[] = $newArr;
            }
        }
        return findCombinations($cnt, $freq, $nums, $num + 1, $arr);
    }
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