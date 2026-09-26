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

class Game
{
    private int $score = 0, $numOfThrows = 0, $numOfGame = 0, $gameScore = 0, $bonus = 0;
    
    public function score(): int
    {
        if ($this->numOfGame == 10 && $this->bonus == 0) {
            return $this->score;
        }
        else {
            throw new Exception();
        }
    }

    public function roll(int $pins): void
    {
        if ($pins < 0) {
            throw new Exception();
        }
        if ($this->numOfGame >= 10) {
            if ($this->bonus == 0) {
                throw new Exception();
            }
            else {
                $this->score += $this->bonus > 2 ? $pins * 2 : $pins;
                $this->bonus -= $this->bonus > 2 ? 2 : 1;
                $this->gameScore += $pins;
                if ($this->gameScore > 10) {
                    throw new Exception();
                }
                if ($this->gameScore == 10) {
                    $this->gameScore = 0;
                }
            }
        }
        else {
            $this->score += $pins * ($this->bonus > 0 ? ($this->bonus > 2 ? 3 : 2) : 1);
            $this->bonus -= $this->bonus == 0 ? 0 : ($this->bonus > 2 ? 2 : 1);
            $this->gameScore += $pins;
            $this->numOfThrows++;
            if ($this->gameScore > 10) {
                throw new Exception();
            }
            if ($this->numOfThrows == 1 && $this->gameScore == 10) {
                $this->bonus += 2;
                $this->numOfGame++;
                $this->numOfThrows = 0;
                $this->gameScore = 0;
            }
            if ($this->numOfThrows == 2) {
                $this->bonus += $this->gameScore == 10 ? 1 : 0;
                $this->numOfGame++;
                $this->numOfThrows = 0;
                $this->gameScore = 0;
            }
        }
    }
}
