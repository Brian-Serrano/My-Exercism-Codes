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

function findFewestCoins(array $coins, int $amount): array
{
    if ($amount == 0)
        return [];
    if ($amount < 0)
        throw new InvalidArgumentException("Cannot make change for negative value");
    if (array_all($coins, fn($x) => $x > $amount))
        throw new InvalidArgumentException("No coins small enough to make change");

    $possibleCoins = array_map(fn($x) => [$x], $coins);

    while (!array_any($possibleCoins, fn($c) => array_sum($c) == $amount)) {
        $possibleCoins = array_values(array_unique(array_merge(...array_map(fn($c) => compute($c, $coins), $possibleCoins)), SORT_REGULAR));

        if (array_all($possibleCoins, fn($c) => array_sum($c) > $amount)) {
            throw new InvalidArgumentException("No combination can add up to target");
        }
    }

    return reset(array_filter($possibleCoins, fn($c) => array_sum($c) == $amount));
}

function compute(array $coin, array $coins): array
{
    $result = [];
    foreach ($coins as $c) {
        $r = $coin;
        $r[] = $c;
        sort($r);
        $result[] = $r;
    }
    return $result;
}
