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

class RobotSimulator
{
    private array $position;
    private string $direction;
    
    /** @param int[] $position */
    public function __construct(array $position, string $direction)
    {
        $this->position = $position;
        $this->direction = $direction;
    }

    public function instructions(string $instructions): void
    {
        for ($i = 0; $i < strlen($instructions); $i++) {
            if ($instructions[$i] == "L") {
                $this->turnLeft();
            }
            else if ($instructions[$i] == "R") {
                $this->turnRight();
            }
            else if ($instructions[$i] == "A") {
                $this->advance();
            }
        }
    }

    private function turnLeft()
    {
        $this->direction = match ($this->direction) {
            "north" => "west",
            "east" => "north",
            "south" => "east",
            "west" => "south",
        };
    }

    private function turnRight()
    {
        $this->direction = match ($this->direction) {
            "north" => "east",
            "east" => "south",
            "south" => "west",
            "west" => "north",
        };
    }

    private function advance()
    {
        $this->position = match ($this->direction) {
            "north" => [$this->position[0], $this->position[1] + 1],
            "east" => [$this->position[0] + 1, $this->position[1]],
            "south" => [$this->position[0], $this->position[1] - 1],
            "west" => [$this->position[0] - 1, $this->position[1]],
        };
    }

    /** @return int[] */
    public function getPosition(): array
    {
        return $this->position;
    }

    public function getDirection(): string
    {
        return $this->direction;
    }
}
