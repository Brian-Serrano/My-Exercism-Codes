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

class SplitSecondStopwatch
{
    public string $state;
    public DateTimeImmutable $total;
    public DateTimeImmutable $currentLap;
    public array $previousLaps;
    
    public function __construct()
    {
        $this->state = "ready";
        $this->total = DateTimeImmutable::createFromFormat("!H:i:s", "00:00:00");
        $this->currentLap = DateTimeImmutable::createFromFormat("!H:i:s", "00:00:00");
        $this->previousLaps = [];
    }

    public function start()
    {
        if ($this->state == "running")
            throw new Exception("cannot start an already running stopwatch");

        $this->state = "running";
    }

    public function stop()
    {
        if ($this->state != "running")
            throw new Exception("cannot stop a stopwatch that is not running");

        $this->state = "stopped";
    }

    public function reset()
    {
        if ($this->state != "stopped")
            throw new Exception("cannot reset a stopwatch that is not stopped");

        $this->state = "ready";
        $this->total = DateTimeImmutable::createFromFormat("!H:i:s", "00:00:00");
        $this->currentLap = DateTimeImmutable::createFromFormat("!H:i:s", "00:00:00");
        $this->previousLaps = [];
    }

    public function lap()
    {
        if ($this->state != "running")
            throw new Exception("cannot lap a stopwatch that is not running");

        $this->previousLaps[] = $this->currentLap->format('H:i:s');
        $this->currentLap = DateTimeImmutable::createFromFormat("!H:i:s", "00:00:00");
    }

    public function getTotal(): string
    {
        return $this->total->format('H:i:s');
    }

    public function getCurrentLap(): string
    {
        return $this->currentLap->format('H:i:s');
    }

    public function advanceTime(string $time)
    {
        if ($this->state == "running") {
            $t = array_map(fn($x) => intval($x), explode(":", $time));
            $timestamp = $t[0] * 3600 + $t[1] * 60 + $t[2];

            $this->currentLap = $this->currentLap->modify("+{$timestamp} seconds");
            $this->total = $this->total->modify("+{$timestamp} seconds");
        }
    }
}
