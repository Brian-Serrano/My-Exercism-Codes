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

class LinkedList
{
    private array $elements;
    
    public function __construct()
    {
        $this->elements = [];
    }

    public function push(int $item)
    {
        array_push($this->elements, $item);
    }

    public function unshift(int $item)
    {
        array_unshift($this->elements, $item);
    }

    public function pop(): int
    {
        return array_pop($this->elements);
    }

    public function shift(): int
    {
        return array_shift($this->elements);
    }

    public function delete(int $item)
    {
        $idx = array_search($item, $this->elements);
        if ($idx !== false)
            array_splice($this->elements, $idx, 1);
    }

    public function count(): int
    {
        return count($this->elements);
    }
}
