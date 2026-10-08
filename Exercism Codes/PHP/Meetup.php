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

function meetup_day(int $year, int $month, string $which, string $weekday): DateTimeImmutable
{
    $lastDay = (int) (new DateTime("$year-$month-01"))->format('t');
    return match ($which) {
        "first" => find_day($year, $month, 1, 7, $weekday),
        "second" => find_day($year, $month, 8, 14, $weekday),
        "third" => find_day($year, $month, 15, 21, $weekday),
        "fourth" => find_day($year, $month, 22, 28, $weekday),
        "last" => find_day($year, $month, $lastDay - 6, $lastDay, $weekday),
        "teenth" => find_day($year, $month, 13, 19, $weekday),
        default => find_day($year, $month, 1, 7, $weekday)
    };
}

function find_day(int $year, int $month, int $start, int $end, string $weekday): DateTimeImmutable
{
    $day = 1;
    for ($n = $start; $n <= $end; $n++) {
        if (new DateTimeImmutable($year."/".$month."/".$n)->format('l') == $weekday) {
            $day = $n;
        }
    }
    return new DateTimeImmutable($year."/".$month."/".$day);
}
