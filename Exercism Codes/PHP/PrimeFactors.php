<?php

declare(strict_types=1);

function factors(int $number): array
{
    $result = [];
    $x = 2;

    while ($number > 1) {
        if ($number % $x === 0) {
            $result[] = $x;
            $number /= $x;
        }
        else {
            $x++;
        }
    }

    return $result;
}
