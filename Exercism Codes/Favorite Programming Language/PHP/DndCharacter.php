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

class DndCharacter
{
    public int $strength, $dexterity, $constitution, $intelligence, $wisdom, $charisma, $hitpoints;
    
    public function __construct()
    {
        $this->strength = $this::ability();
        $this->dexterity = $this::ability();
        $this->constitution = $this::ability();
        $this->intelligence = $this::ability();
        $this->wisdom = $this::ability();
        $this->charisma = $this::ability();
        $this->hitpoints = 10 + $this::modifier($this->constitution);
    }

    public static function modifier(int $number): int
    {
        return intval(floor(($number - 10) / 2));
    }

    public static function ability(): int
    {
        $numbers = array_map(fn() => random_int(1, 6), range(1, 4));
        sort($numbers);
        return array_sum(array_slice($numbers, 1));
    }

    public static function generate(): DndCharacter
    {
        return new DndCharacter();
    }
}
