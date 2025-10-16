<?php

declare(strict_types=1);

function translate(string $text): string
{
    $result = [];

    foreach (explode(" ", $text) as $x) {
        $sl = 0;

        while (true) {
            $is_vowel = str_contains("aeiou", $x[$sl]);
            $rule_1 = substr($x, $sl, 2) === "yt" || substr($x, $sl, 2) === "xr";
            $rule_4 = (!str_contains("aeiou", $x[$sl - 1]) && $sl - 1 >= 0) && $x[$sl] === "y";

            if ($is_vowel || $rule_1 || $rule_4) {
                break;
            }

            if (substr($x, $sl, 2) === "qu") {
                $sl += 1;
            }

            $sl += 1;
        }

        $result[] = substr($x, $sl, strlen($x) - $sl) . substr($x, 0, $sl) . "ay";
    }

    return implode(" ", $result);
}
