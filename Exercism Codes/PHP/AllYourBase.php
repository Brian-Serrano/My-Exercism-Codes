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

function rebase(int $fromBase, array $digits, int $toBase): array
{
    if ($fromBase < 2) {
        throw new InvalidArgumentException("input base must be >= 2");
    }
    if ($toBase < 2) {
        throw new InvalidArgumentException("output base must be >= 2");
    }

    $result = [];
    $dec = 0;
    $n = count($digits);

    for ($i = 0; $i < $n; $i++) {
        if ($digits[$i] < 0 || $digits[$i] >= $fromBase) {
            throw new InvalidArgumentException("all digits must satisfy 0 <= d < input base");
        }
        $dec += pow($fromBase, $n - $i - 1) * $digits[$i];
    }

    while ($dec > 0) {
        array_unshift($result, $dec % $toBase);
        $dec = intdiv($dec, $toBase);
    }

    return count($result) > 0 ? $result : [0];
}
