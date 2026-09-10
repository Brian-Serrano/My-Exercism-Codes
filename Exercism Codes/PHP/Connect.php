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

function winner(array $lines): ?string
{
    $rows = array_map(function($x) {
        return explode(" ", trim($x));
    }, $lines);

    $check = function($row, $col, $visited, $l) use ($rows, &$check) {
        if ($l == "X" ? $col == count($rows[0]) : $row == count($rows)) {
            return true;
        }
        if (!($row >= 0 && $row < count($rows)) || !($col >= 0 && $col < count($rows[0])) || $visited[$row][$col]) {
            return false;
        }
        $visited[$row][$col] = true;

        if ($rows[$row][$col] == $l) {
            return $check($row + 1, $col, $visited, $l) ||
                $check($row - 1, $col, $visited, $l) ||
                $check($row, $col + 1, $visited, $l) ||
                $check($row, $col - 1, $visited, $l) ||
                $check($row + 1, $col - 1, $visited, $l) ||
                $check($row - 1, $col + 1, $visited, $l);
        }

        return false;
    };

    $xWins = false;
    $oWins = false;

    for ($x = 0; $x < count($rows); $x++) {
        if ($rows[$x][0] == "X") {
            $wins = $check($x, 0, createVisited($rows), "X");
            if ($wins) {
                $xWins = true;
                break;
            }
        }
    }

    for ($o = 0; $o < count($rows[0]); $o++) {
        if ($rows[0][$o] == "O") {
            $wins = $check(0, $o, createVisited($rows), "O");
            if ($wins) {
                $oWins = true;
                break;
            }
        }
    }

    if ($xWins) {
        return "black";
    }
    else if ($oWins) {
        return "white";
    }
    else {
        return null;
    }
}

function createVisited(array $rows): array {
    $result = [];
    
    for ($x = 0; $x < count($rows); $x++) {
        $z = [];
        for ($y = 0; $y < count($rows[$x]); $y++) {
            $z[$y] = false;
        }
        $result[$x] = $z;
    }

    return $result;
}
