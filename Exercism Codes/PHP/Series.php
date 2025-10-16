<?php

declare(strict_types=1);

function slices(string $digits, int $series): array
{
    if ($series === 0 || $series > strlen($digits)) {
        throw new Exception();
    }

    $result = [];
    for ($i = 0; $i <= strlen($digits) - $series; $i++) {
        $result[] = substr($digits, $i, $series);
    }
    return $result;
}
