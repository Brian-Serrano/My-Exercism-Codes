<?php

declare(strict_types=1);

function brackets_match(string $input): bool
{
    $stack = [];

    foreach (str_split($input) as $char) {
        if (str_contains("([{", $char)) {
            array_push($stack, $char);
        }
        $symbols = [")" => "(", "]" => "[", "}" => "{"];
        foreach ($symbols as $x => $y) {
            if ($char === $x) {
                if (count($stack) > 0 && $stack[count($stack) - 1] === $y) {
                    array_pop($stack);
                }
                else {
                    return false;
                }
            }
        }
    }

    return count($stack) === 0;
}
