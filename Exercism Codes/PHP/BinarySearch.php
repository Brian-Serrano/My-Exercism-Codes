<?php

declare(strict_types=1);

function find(int $needle, array $haystack): int
{
    $left = 0;
    $right = count($haystack) - 1;

    while ($left <= $right) {
        $mid = $left + intval(($right - $left) / 2);

        if ($haystack[$mid] === $needle) {
            return $mid;
        }

        if ($haystack[$mid] > $needle) {
            $right = $mid - 1;
        }

        if ($haystack[$mid] < $needle) {
            $left = $mid + 1;
        }
    }

    return -1;
}
