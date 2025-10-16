<?php

declare(strict_types=1);

function encode(string $text): string
{
    $result = [];
    foreach (str_split($text) as $x) {
        if (preg_match("/\w/", $x)) {
            $result[] = transform($x);

            if ((count($result) + 1) % 6 === 0) {
                $result[] = " ";
            }
        }
    }
    return trim(implode("", $result));
}

function decode(string $text): string
{
    $result = [];
    foreach (str_split($text) as $x) {
        if (preg_match("/\w/", $x)) {
            $result[] = transform($x);
        }
    }
    return implode("", $result);
}

function transform(string $x): string
{
    return preg_match("/[A-Za-z]/", $x) ? mb_chr((26 - (mb_ord(strtolower($x)) - 97) - 1) + 97) : $x;
}