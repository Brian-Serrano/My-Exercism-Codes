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

function smallest(int $min, int $max): array
{
    if ($min > $max)
        throw new Exception();

    $minimum = null;
    $factors = [];

    for ($i = $min; $i <= $max; $i++) {
        for ($j = $i; $j <= $max; $j++) {
            $product = $i * $j;

            if ($minimum != null && $product > $minimum)
                continue;

            if (isPalindrome($product)) {
                if ($minimum == null || $product < $minimum) {
                    $minimum = $product;
                    $factors = [];
                }
                $factors[] = [$i, $j];
            }
        }
    }
    if ($minimum != null)
        return [$minimum, $factors];
    else
        throw new Exception();
}

function largest(int $min, int $max): array
{
    if ($min > $max)
        throw new Exception();

    $maximum = null;
    $factors = [];

    for ($i = $min; $i <= $max; $i++) {
        for ($j = $i; $j <= $max; $j++) {
            $product = $i * $j;

            if ($maximum != null && $product < $maximum)
                continue;

            if (isPalindrome($product)) {
                if ($maximum == null || $product > $maximum) {
                    $maximum = $product;
                    $factors = [];
                }
                $factors[] = [$i, $j];
            }
        }
    }
    if ($maximum != null)
        return [$maximum, $factors];
    else
        throw new Exception();
}

function isPalindrome(int $num): bool
{
    return strval($num) == strrev(strval($num));
}
