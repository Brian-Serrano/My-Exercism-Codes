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

class GameOfLife
{
    public array $matrix;
    
    public function __construct(array $matrix)
    {
        $this->matrix = $matrix;
    }

    public function tick(): void
    {
        $elToChange = [];
        for ($i = 0; $i < count($this->matrix); $i++) {
            for ($j = 0; $j < count($this->matrix[$i]); $j++) {
                $k = 0;
                if ($i + 1 < count($this->matrix) && $this->matrix[$i + 1][$j] == 1) $k++;
                if ($i - 1 >= 0 && $this->matrix[$i - 1][$j] == 1) $k++;
                if ($j + 1 < count($this->matrix[$i]) && $this->matrix[$i][$j + 1] == 1) $k++;
                if ($j - 1 >= 0 && $this->matrix[$i][$j - 1] == 1) $k++;
                if ($i + 1 < count($this->matrix) && $j + 1 < count($this->matrix[$i]) && $this->matrix[$i + 1][$j + 1] == 1) $k++;
                if ($i + 1 < count($this->matrix) && $j - 1 >= 0 && $this->matrix[$i + 1][$j - 1] == 1) $k++;
                if ($i - 1 >= 0 && $j + 1 < count($this->matrix[$i]) && $this->matrix[$i - 1][$j + 1] == 1) $k++;
                if ($i - 1 >= 0 && $j - 1 >= 0 && $this->matrix[$i - 1][$j - 1] == 1) $k++;

                if ($this->matrix[$i][$j] == 1 && $k < 2 || $k > 3)
                    $elToChange[] = [$i, $j, 0];
                else if ($this->matrix[$i][$j] == 0 && $k == 3)
                    $elToChange[] = [$i, $j, 1];
            }
        }
        foreach ($elToChange as $el) {
            $this->matrix[$el[0]][$el[1]] = $el[2];
        }
    }
}
