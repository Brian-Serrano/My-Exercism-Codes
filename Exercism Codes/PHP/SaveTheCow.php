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

class SaveTheCow
{
    public int $remainingFailures;
    public string $maskedWord;
    public string $state;
    private array $letters;
    private string $word;
    
    public function __construct(string $word)
    {
        $this->remainingFailures = 9;
        $this->maskedWord = str_repeat("_", strlen($word));
        $this->state = "Ongoing";
        $this->letters = [];
        $this->word = $word;
    }

    public function guess(string $char)
    {
        if ($this->state == "Lose")
            throw new Exception("cannot guess after the game is lost");
        if ($this->state == "Win")
            throw new Exception("cannot guess after the game is won");
        
        $guessedCorrect = false;

        for ($i = 0; $i < strlen($this->word); $i++) {
            if ($this->word[$i] == $char && !in_array($char, $this->letters)) {
                $this->maskedWord = $this->setMask($i, $char);
                $guessedCorrect = true;
            }
        }

        if (!str_contains($this->maskedWord, "_"))
            $this->state = "Win";

        if (!$guessedCorrect) {
            $this->remainingFailures--;

            if ($this->remainingFailures < 0) {
                $this->state = "Lose";
                $this->remainingFailures = 0;
            }
        }
        else {
            $this->letters[] = $char;
        }
    }

    private function setMask(int $idx, string $char): string
    {
        $result = str_split($this->maskedWord);

        for ($i = 0; $i < count($result); $i++) {
            if ($i == $idx) {
                $result[$i] = $char;
            }
        }

        return implode("", $result);
    }
}
