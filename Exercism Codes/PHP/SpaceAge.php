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

class SpaceAge
{
    private int $seconds;
    
    public function __construct(int $seconds)
    {
        $this->seconds = $seconds;
    }

    public function earth(): float
    {
        return round($this->seconds / (1.0 * 31557600), 2);
    }

    public function mercury(): float
    {
        return round($this->seconds / (0.2408467 * 31557600), 2);
    }

    public function venus(): float
    {
        return round($this->seconds / (0.61519726 * 31557600), 2);
    }

    public function mars(): float
    {
        return round($this->seconds / (1.8808158 * 31557600), 2);
    }

    public function jupiter(): float
    {
        return round($this->seconds / (11.862615 * 31557600), 2);
    }

    public function saturn(): float
    {
        return round($this->seconds / (29.447498 * 31557600), 2);
    }

    public function uranus(): float
    {
        return round($this->seconds / (84.016846 * 31557600), 2);
    }

    public function neptune(): float
    {
        return round($this->seconds / (164.79132 * 31557600), 2);
    }
}
