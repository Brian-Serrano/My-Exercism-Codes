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

function encode(string $text, int $num1, int $num2): string
{
    if ($num1 % 2 == 0 or $num1 % 13 == 0) {
        throw new Exception();
    }
    $e = str_split(preg_replace("/\W/", "", strtolower($text)));
    $enc = "";
    foreach ($e as $x) {
        $y = ord($x) >= 48 && ord($x) <= 57 ? ord($x) - ord("a") : (($num1 * (ord($x) - ord("a"))) + $num2) % 26;
        $enc .= chr($y + ord("a"));
    }
    preg_match_all("/.{1,5}/", $enc, $matches);
    return implode(" ", $matches[0]);
}

function decode(string $text, int $num1, int $num2): string
{
    if ($num1 % 2 == 0 or $num1 % 13 == 0) {
        throw new Exception();
    }
    $d = str_split(preg_replace("/\W/", "", strtolower($text)));
    $dec = "";
    foreach ($d as $x) {
        $y = ord($x) >= 48 && ord($x) <= 57 ? ord($x) : (mmi($num1) * ((ord($x) - ord("a")) - $num2)) % 26;
        $dec .= $y >= 48 && $y <= 57 ? chr($y) : chr((($y + 26) % 26) + ord("a"));
    }
    return $dec;
}

function mmi(int $a): int {
    for ($i = 1; $i < 26; $i++) {
        if ($a * $i % 26 == 1) {
            return $i;
        }
    }
    return -1;
}
