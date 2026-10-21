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

function transmitSequence(array $sequence): array
{
    $binString = implode("", array_map(
        fn($b) => str_pad(decbin(hexdec($b)), 8, '0', STR_PAD_LEFT),
        $sequence
    ));
    $result = getMatches($binString, "/.{1,7}/");
    for ($i = 0; $i < count($result); $i++) {
        $padded = str_pad($result[$i], 7, '0', STR_PAD_RIGHT);
        $padded .= array_count_values(str_split($padded))["1"] % 2;
        $result[$i] = $padded;
    }
    return array_map(
        fn($m) => "0x".str_pad(dechex(bindec($m)), 2, '0', STR_PAD_LEFT),
        $result
    );
}

function decodeMessage(array $message): array
{
    if (array_any($message, fn($s) => substr_count(decbin(hexdec($s)), '1') % 2 != 0))
        throw new Exception("wrong parity");

    $binString = implode("", array_map(
        function($b) {
            $s = str_pad(decbin(hexdec($b)), 8, '0', STR_PAD_LEFT);
            return substr($s, 0, strlen($s) - 1);
        }, 
        $message
    ));
    $result = getMatches($binString, "/.{8}/");
    return array_map(
        fn($m) => "0x".str_pad(dechex(bindec($m)), 2, '0', STR_PAD_LEFT),
        $result
    );
}

function getMatches(string $text, string $pattern): array
{
    preg_match_all($pattern, $text, $result);
    return $result[0];
}
