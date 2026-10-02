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

class Clock
{
    private int $hour;
    private int $minute;
    
    public function __construct(int $hour = 0, int $minute = 0)
    {
        $today = new DateTime('today');
        $today->modify("+".$hour." hours ".$minute." minutes");
        $this->hour = intval($today->format('G'));
        $this->minute = intval($today->format('i'));
    }

    public function add(int $minute): Clock
    {
        return $this->modify($minute, "+");
    }

    public function sub(int $minute): Clock
    {
        return $this->modify($minute, "-");
    }

    private function modify(int $minute, string $op): Clock
    {
        $today = new DateTime('today');
        $today->modify("+".$this->hour." hours ".$this->minute." minutes");
        $today->modify($op.$minute." minutes");
        $this->hour = intval($today->format('G'));
        $this->minute = intval($today->format('i'));
        return $this;
    }
    
    public function __toString(): string
    {
        return sprintf("%02d:%02d", $this->hour, $this->minute);
    }
}
