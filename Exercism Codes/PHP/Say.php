<?php

declare(strict_types=1);

function join_array(array $lst): string
{
    $ones = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];
    $tens = ["ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];

    $result = $lst[0];

    for ($x = 1; $x < count($lst); $x++) {
        if (in_array($lst[$x - 1], $tens) && in_array($lst[$x], $ones)) {
            $result .= "-" . $lst[$x];
        }
        else {
            $result .= " " . $lst[$x];
        }
    }
    return $result;
}

function say(int $number): string
{
    $ones = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];
    $tens = ["ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];
    $teens = ["eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"];

    if ($number === 0) {
        return "zero";
    }
    if ($number < 0 || $number > 999999999999) {
        throw new InvalidArgumentException("Input out of range");
    }

    $result = [];
    $str = strval($number);

    for ($x = strlen($str) - 1; $x >= 0; $x--) {
        $idx = strlen($str) - $x - 1;
        if ($idx / 3 === 1) {
            array_unshift($result, "thousand");
        }
        if ($idx / 3 === 2) {
            if (intval(substr($str, $x + 1, 3)) === 0) {
                array_shift($result);
            }
            array_unshift($result, "million");
        }
        if ($idx / 3 === 3) {
            if (intval(substr($str, $x + 1, 3)) === 0) {
                array_shift($result);
            }
            array_unshift($result, "billion");
        }

        if (intval($str[$x]) !== 0) {
            if ($idx % 3 === 0) {
                array_unshift($result, $ones[intval($str[$x]) - 1]);
            }
            if ($idx % 3 === 1) {
                if (intval($str[$x]) === 1 && intval($str[$x + 1]) !== 0) {
                    array_shift($result);
                    array_unshift($result, $teens[intval($str[$x + 1]) - 1]);
                }
                else {
                    array_unshift($result, $tens[intval($str[$x]) - 1]);
                }
            }
            if ($idx % 3 === 2) {
                array_unshift($result, "hundred");
                array_unshift($result, $ones[intval($str[$x]) - 1]);
            }
        }
    }

    return join_array($result);
}