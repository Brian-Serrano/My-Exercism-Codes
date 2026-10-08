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

class House
{
    private const PHRASES = [
        " the house that Jack built.",
        " the malt",
        " the rat",
        " the cat",
        " the dog",
        " the cow with the crumpled horn",
        " the maiden all forlorn",
        " the man all tattered and torn",
        " the priest all shaven and shorn",
        " the rooster that crowed in the morn",
        " the farmer sowing his corn",
        " the horse and the hound and the horn"
    ];

    private const WORDS = [
        "lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"
    ];
    
    public function verse(int $verseNumber): array
    {
        $phraseGroup = [];
        for ($i = $verseNumber - 1; $i >= 0; $i--) {
            $phraseGroup[] = ($i == $verseNumber - 1 ? "This is" : "that " . self::WORDS[$i]) . self::PHRASES[$i];
        }
        return $phraseGroup;
    }

    public function verses(int $start, int $end): array
    {
        $phraseGroups = [];
        for ($i = $start; $i <= $end; $i++) {
            $phraseGroups = array_merge($phraseGroups, $this->verse($i));
            if ($i != $end)
                $phraseGroups[] = "";
        }
        return $phraseGroups;
    }
}
