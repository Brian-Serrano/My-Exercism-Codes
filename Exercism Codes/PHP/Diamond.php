<?php

declare(strict_types=1);

function diamond(string $letter): array
{
    $result = [];
    for ($x = 65; $x <= mb_ord($letter); $x++) {
        $result[] = line($letter, $x);
    }
    for ($x = mb_ord($letter) - 1; $x >= 65; $x--) {
        $result[] = line($letter, $x);
    }
    return $result;
}

function line(string $letter, int $x): string {
    $pad1 = str_repeat(" ", (mb_ord($letter) - 65) - ($x - 65));
    $pad2 = str_repeat(" ", $x - 65);
    $first_half_line = $pad1 . mb_chr($x) . $pad2;
    $second_half_line = $pad2 . mb_chr($x) . $pad1;
    return $first_half_line . substr($second_half_line, 1, strlen($second_half_line) - 1);
}