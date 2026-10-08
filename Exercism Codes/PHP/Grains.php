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

function square(int $number): string
{
    if ($number < 1 || $number > 64) {
        throw new InvalidArgumentException();
    }

    $result = '1';

    for ($i = 1; $i < $number; $i++) {
        $result = doubleNumber($result);
    }

    return $result;
}

function total(): string
{
    $result = '1';

    for ($i = 0; $i < 64; $i++) {
        $result = doubleNumber($result);
    }

    return subtractOne($result);
}

function doubleNumber(string $number): string
{
    $carry = 0;
    $result = '';

    for ($i = strlen($number) - 1; $i >= 0; $i--) {
        $value = ((int) $number[$i] * 2) + $carry;

        $result = ($value % 10) . $result;
        $carry = intdiv($value, 10);
    }

    if ($carry > 0) {
        $result = $carry . $result;
    }

    return $result;
}

function subtractOne(string $number): string
{
    $digits = str_split($number);
    $i = count($digits) - 1;

    while ($digits[$i] === '0') {
        $digits[$i] = '9';
        $i--;
    }

    $digits[$i]--;

    return ltrim(implode('', $digits), '0') ?: '0';
}