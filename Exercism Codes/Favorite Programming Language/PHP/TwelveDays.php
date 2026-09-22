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

class TwelveDays
{
    public array $phrases = [
        "a Partridge in a Pear Tree",
        "two Turtle Doves",
        "three French Hens",
        "four Calling Birds",
        "five Gold Rings",
        "six Geese-a-Laying",
        "seven Swans-a-Swimming",
        "eight Maids-a-Milking",
        "nine Ladies Dancing",
        "ten Lords-a-Leaping",
        "eleven Pipers Piping",
        "twelve Drummers Drumming"
    ];
    public array $number = [
        "first", "second", "third",
        "fourth", "fifth", "sixth",
        "seventh", "eighth", "ninth",
        "tenth", "eleventh", "twelfth"
    ];
    
    public function recite(int $start, int $end): string
    {
        $verse = "";
        for ($i = $start; $i <= $end; $i++) {
            $seperator = $i == $end ? "" : "\n";
            $verse .= $this->generateVerse($i) . $seperator;
        }
        return $verse;
    }

    public function generateVerse(int $verseNumber): string
    {
        $verse = "On the " . $this->number[$verseNumber - 1] . " day of Christmas my true love gave to me: ";
        for ($i = $verseNumber - 1; $i >= 0; $i--) {
            $seperator = $i == 0 ? "" : ", ";
            if ($i == 0 && $verseNumber > 1) {
                $verse .= "and ";
            }
            $verse .= $this->phrases[$i] . $seperator;
        }
        $verse .= ".";
        return $verse;
    }
}
