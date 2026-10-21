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

function recognize(array $input): string
{
    if (count($input) % 4 != 0)
        throw new InvalidArgumentException();
    if (strlen($input[0]) % 3 != 0)
        throw new InvalidArgumentException();

    $readers = [
        fn($t) => $t === [" _ ", "| |", "|_|", "   "],
        fn($t) => $t === ["   ", "  |", "  |", "   "],
        fn($t) => $t === [" _ ", " _|", "|_ ", "   "],
        fn($t) => $t === [" _ ", " _|", " _|", "   "],
        fn($t) => $t === ["   ", "|_|", "  |", "   "],
        fn($t) => $t === [" _ ", "|_ ", " _|", "   "],
        fn($t) => $t === [" _ ", "|_ ", "|_|", "   "],
        fn($t) => $t === [" _ ", "  |", "  |", "   "],
        fn($t) => $t === [" _ ", "|_|", "|_|", "   "],
        fn($t) => $t === [" _ ", "|_|", " _|", "   "]
    ];

    $cropped = crop($input);
    $s = "";

    for ($j = 0; $j < count($cropped); $j++) {
        for ($k = 0; $k < count($cropped[$j]); $k++) {
            for ($i = 0; $i <= count($readers); $i++) {
                if ($i == count($readers))
                    $s .= "?";
                else if ($readers[$i]($cropped[$j][$k])) {
                    $s .= $i;
                    break;
                }
            }
        }
        if ($j != count($cropped) - 1)
            $s .= ",";
    }
    return $s;
}

function crop(array $text): array
{
    $result = [];

    for ($i = 0; $i < count($text); $i += 4) {
        $result[] = array_slice($text, $i, 4);
    }

    return array_map(fn($r) => zip(array_map(fn($s) => getMatch($s), $r)), $result);
}

function zip(array $input): array
{
    return array_map(fn($t) => array_map(fn($l) => $l[$t], $input), range(0, count($input[0]) - 1));
}

function getMatch(string $text): array
{
    preg_match_all("/.{3}/", $text, $matches);
    return $matches[0];
}
