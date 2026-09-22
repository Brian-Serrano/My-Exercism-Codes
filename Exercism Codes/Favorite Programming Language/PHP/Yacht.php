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

class Yacht
{
    public function score(array $rolls, string $category): int
    {
        $categories = ['yacht', 'ones', 'twos', 'threes', 'fours', 'fives', 'sixes', 'full house', 'four of a kind', 'little straight', 'big straight', 'choice'];
        
        $functions = [
            fn($x) => count(array_unique($x)) == 1 ? 50 : 0,
            fn($x) => array_sum(array_map(fn($d) => $d == 1 ? $d : 0, $x)),
            fn($x) => array_sum(array_map(fn($d) => $d == 2 ? $d : 0, $x)),
            fn($x) => array_sum(array_map(fn($d) => $d == 3 ? $d : 0, $x)),
            fn($x) => array_sum(array_map(fn($d) => $d == 4 ? $d : 0, $x)),
            fn($x) => array_sum(array_map(fn($d) => $d == 5 ? $d : 0, $x)),
            fn($x) => array_sum(array_map(fn($d) => $d == 6 ? $d : 0, $x)),
            function($x) {
                $arr1 = [2, 3];
                $arr2 = array_map(fn($d) => count(array_filter($x, fn($y) => $y == $d)), array_unique($x));
                return empty(array_diff($arr1, $arr2)) && empty(array_diff($arr2, $arr1)) ? array_sum($x) : 0;
            },
            fn($x) => array_sum(array_filter(array_unique($x), fn($d) => count(array_filter($x, fn($y) => $y == $d)) >= 4)) * 4,
            fn($x) => empty(array_diff([1, 2, 3, 4, 5], $x)) && empty(array_diff($x, [1, 2, 3, 4, 5])) ? 30 : 0,
            fn($x) => empty(array_diff([2, 3, 4, 5, 6], $x)) && empty(array_diff($x, [2, 3, 4, 5, 6])) ? 30 : 0,
            fn($x) => array_sum($x)
        ];

        return $functions[array_search($category, $categories)]($rolls);
    }
}
