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

class WordSearch
{
    // This exercises uses additional files for data classes.
    // In the online editor these are available as additional
    // tabs next to this file's tab.

    private array $grid;

    public function __construct(array $grid)
    {
        $this->grid = $grid;
    }

    public function search(string $word): ?Result
    {
        $ln = strlen($word);
        $rl = count($this->grid);
        for ($i = 0; $i < $rl; $i++) {
            $cl = strlen($this->grid[$i]);
            for ($j = 0; $j < $cl; $j++) {
                if ($word[0] == $this->grid[$i][$j]) {
                    if ($this->srch($word, $ln <= $i + 1, $i, $j, -1, 0)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j + 1, $i - ($ln - 1) + 1));
                    }
                    if ($this->srch($word, $ln <= $j + 1, $i, $j, 0, -1)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j - ($ln - 1) + 1, $i + 1));
                    }
                    if ($this->srch($word, $ln <= $rl - $i, $i, $j, 1, 0)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j + 1, $i + ($ln - 1) + 1));
                    }
                    if ($this->srch($word, $ln <= $cl - $j, $i, $j, 0, 1)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j + ($ln - 1) + 1, $i + 1));
                    }
                    if ($this->srch($word, $ln <= $i + 1 && $ln <= $j + 1, $i, $j, -1, -1)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j - ($ln - 1) + 1, $i - ($ln - 1) + 1));
                    }
                    if ($this->srch($word, $ln <= $i + 1 && $ln <= $cl - $j, $i, $j, -1, 1)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j + ($ln - 1) + 1, $i - ($ln - 1) + 1));
                    }
                    if ($this->srch($word, $ln <= $rl - $i && $ln <= $j + 1, $i, $j, 1, -1)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j - ($ln - 1) + 1, $i + ($ln - 1) + 1));
                    }
                    if ($this->srch($word, $ln <= $rl - $i && $ln <= $cl - $j, $i, $j, 1, 1)) {
                        return new Result(new Location($j + 1, $i + 1), new Location($j + ($ln - 1) + 1, $i + ($ln - 1) + 1));
                    }
                }
            }
        }
        return null;
    }

    private function srch(string $word, bool $condition, int $x, int $y, int $xOffset, int $yOffset): bool
    {
        if ($condition) {
            $cnt = 1;
            for ($i = 1; $i < strlen($word); $i++) {
                if ($word[$i] == $this->grid[$x + ($i * $xOffset)][$y + ($i * $yOffset)]) {
                    $cnt++;
                }
            }
            return $cnt == strlen($word);
        }
        return false;
    }
}
