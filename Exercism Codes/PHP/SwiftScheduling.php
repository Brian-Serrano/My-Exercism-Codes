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

class SwiftScheduling
{
    public DateTime $meetingStart;
    
    public function __construct(DateTime $meetingStart)
    {
        $this->meetingStart = $meetingStart;
    }

    public function deliveryDate(string $description): DateTime
    {
        if ($description == "NOW") {
            return $this->meetingStart->modify("+2 hours");
        }
        if ($description == "ASAP") {
            return $this->meetingStart->modify(intval($this->meetingStart->format("G")) < 13 ? "17:00:00" : "+1 day 13:00:00");
        }
        if ($description == "EOW") {
            $dayOfWeek = intval($this->meetingStart->format("N"));
            return $this->meetingStart->modify(($dayOfWeek >= 1 && $dayOfWeek <= 3) ? "next Friday 17:00:00" : "next Sunday 20:00:00");
        }
        if ($description[strlen($description) - 1] == "M") {
            $n = intval(substr($description, 0, strlen($description) - 1));
            if (intval($this->meetingStart->format("n")) >= $n) {
                $this->meetingStart->modify("+1 year");
            }
            $this->meetingStart->setDate(intval($this->meetingStart->format("Y")), $n, 1);
            return $this->findWorkday($this->meetingStart, false);
        }
        if ($description[0] == "Q") {
            $n = intval(substr($description, 1, strlen($description) - 1));
            if (intval($this->meetingStart->format("n")) >= $n * 3 + 1) {
                $this->meetingStart->modify("+1 year");
            }
            $this->meetingStart->setDate(intval($this->meetingStart->format("Y")), $n * 3, 1);
            return $this->findWorkday($this->meetingStart->modify("+1 month -1 day"), true);
        }
        return new DateTime();
    }

    private function findWorkday(DateTime $date, bool $reverse): DateTime {
        while (!in_array(intval($date->format("N")), [1, 2, 3, 4, 5])) {
            $date->modify($reverse ? "-1 day" : "+1 day");
        }
        return $date->modify("08:00:00");
    }
}
