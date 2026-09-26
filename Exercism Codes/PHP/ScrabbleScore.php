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

function score(string $word): int
{
    $w = strtolower($word);
    $points = 0;
    for ($i = 0; $i < strlen($w); $i++) {
        if (str_contains("aeioulnrst", $w[$i])) {
            $points += 1;
        }
        else if (str_contains("dg", $w[$i])) {
            $points += 2;
        }
        else if (str_contains("bcmp", $w[$i])) {
            $points += 3;
        }
        else if (str_contains("fhvwy", $w[$i])) {
            $points += 4;
        }
        else if (str_contains("k", $w[$i])) {
            $points += 5;
        }
        else if (str_contains("jx", $w[$i])) {
            $points += 8;
        }
        else if (str_contains("qz", $w[$i])) {
            $points += 10;
        }
    }
    return $points;
}
