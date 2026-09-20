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

class PhoneNumber
{
    private string $number;
    
    public function __construct(string $num)
    {
        $n = preg_replace("/[\\s\\-()\\.+]/", "", $num);
        if (strlen($n) == 11) {
            if ($n[0] != "1") {
                throw new InvalidArgumentException("11 digits must start with 1");
            }
            else {
                $n = substr($n, 1);
            }
        }
        else if (strlen($n) > 11) {
            throw new InvalidArgumentException("must not be greater than 11 digits");
        }
        else if (strlen($n) < 10) {
            throw new InvalidArgumentException("must not be fewer than 10 digits");
        }

        if (preg_match('/\p{P}/u', $n) === 1) {
            throw new InvalidArgumentException("punctuations not permitted");
        }
        if (preg_match('/[a-zA-Z]/', $n) === 1) {
            throw new InvalidArgumentException("letters not permitted");
        }
        if ($n[0] == "0") {
            throw new InvalidArgumentException("area code cannot start with zero");
        }
        if ($n[0] == "1") {
            throw new InvalidArgumentException("area code cannot start with one");
        }
        if ($n[3] == "0") {
            throw new InvalidArgumentException("exchange code cannot start with zero");
        }
        if ($n[3] == "1") {
            throw new InvalidArgumentException("exchange code cannot start with one");
        }

        $this->number = $n;
    }
    
    public function number(): string
    {
        return $this->number;
    }
}
