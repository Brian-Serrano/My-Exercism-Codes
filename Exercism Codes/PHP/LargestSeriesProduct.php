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

class Series
{
    private string $input;
    
    public function __construct(string $input)
    {
        $this->input = $input;
    }

    public function largestProduct(int $span): int
    {
        if ($span > strlen($this->input) || $span < 0 || preg_match("/.*[a-zA-Z].*/", $this->input))
            throw new InvalidArgumentException();

        $subnumbers = [];
        for ($i = 0; $i + $span <= strlen($this->input); $i++)
            $subnumbers[] = substr($this->input, $i, $span);

        return max(array_map(fn($x) => array_product(array_map(fn($y) => intval($y), str_split($x))), $subnumbers));
    }
}
