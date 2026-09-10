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

function toRoman(int $number): string
{
    $result = "";
    $dct = [
        "M" => 1000, "CM" => 900, "D" => 500, 
        "CD" => 400, "C" => 100, "XC" => 90, 
        "L" => 50, "XL" => 40, "X" => 10, 
        "IX" => 9, "V" => 5, "IV" => 4, "I" => 1
    ];

    while ($number > 0) {
        foreach ($dct as $k => $v) {
            if ($number >= $v) {
                $result .= $k;
                $number -= $v;
                break;
            }
        }
    }

    return $result;
}
