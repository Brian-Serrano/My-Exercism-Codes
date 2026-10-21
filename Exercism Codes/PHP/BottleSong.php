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

class BottleSong
{
    private array $numbers = [
        ["One", "no"],
        ["Two", "one"],
        ["Three", "two"],
        ["Four", "three"],
        ["Five", "four"],
        ["Six", "five"],
        ["Seven", "six"],
        ["Eight", "seven"],
        ["Nine", "eight"],
        ["Ten", "nine"],
    ];
    
    public function verse(int $number): string
    {
        $result = "";

        $n = $this->numbers[$number - 1][0];
        $bottle1 = $n == "One" ? "bottle" : "bottles";
        $result .= "$n green $bottle1 hanging on the wall,\n";
        $result .= "$n green $bottle1 hanging on the wall,\n";

        $result .= "And if one green bottle should accidentally fall,\n";

        $s = $this->numbers[$number - 1][1];
        $bottle2 = $s == "one" ? "bottle" : "bottles";
        $result .= "There'll be $s green $bottle2 hanging on the wall.";

        return $result;
    }

    public function verses(int $start, int $size): string
    {
        $result = "";

        for ($i = $start; $i > $start - $size; $i--) {
            $result .= $this->verse($i);

            if ($i != $start - $size + 1)
                $result .= "\n\n";
        }

        return $result;
    }

    public function lyrics(): string
    {
        return $this->verses(10, 10);
    }
}
