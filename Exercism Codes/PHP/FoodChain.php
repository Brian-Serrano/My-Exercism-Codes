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

class FoodChain
{
    private const PHRASES = [
    ["I know an old lady who swallowed a fly.", "I don't know why she swallowed the fly. Perhaps she'll die."],
    ["I know an old lady who swallowed a spider.", "It wriggled and jiggled and tickled inside her."],
    ["I know an old lady who swallowed a bird.", "How absurd to swallow a bird!"],
    ["I know an old lady who swallowed a cat.", "Imagine that, to swallow a cat!"],
    ["I know an old lady who swallowed a dog.", "What a hog, to swallow a dog!"],
    ["I know an old lady who swallowed a goat.", "Just opened her throat and swallowed a goat!"],
    ["I know an old lady who swallowed a cow.", "I don't know how she swallowed a cow!"],
    ["I know an old lady who swallowed a horse.", "She's dead, of course!"]
    ];

    private const OTHER_PHRASES = [
    ["She swallowed the spider to catch the fly.", "I don't know why she swallowed the fly. Perhaps she'll die."],
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    "She swallowed the cat to catch the bird.",
    "She swallowed the dog to catch the cat.",
    "She swallowed the goat to catch the dog.",
    "She swallowed the cow to catch the goat."
    ];
    
    public function verse(int $verseNumber): array
    {
        $phraseGroup = [];
        if ($verseNumber > 7)
            return self::PHRASES[7];

        for ($i = $verseNumber - 1; $i >= 0; $i--) {
            if ($i == $verseNumber - 1) {
                $phraseGroup = array_merge($phraseGroup, self::PHRASES[$i]);
            }
            else {
                if ($i == 0) {
                    $phraseGroup = array_merge($phraseGroup, self::OTHER_PHRASES[$i]);
                }
                else {
                    $phraseGroup[] = self::OTHER_PHRASES[$i];
                }
            }
        }
        return $phraseGroup;
    }

    public function verses(int $start, int $end): array
    {
        $phraseGroups = [];
        for ($i = $start; $i <= $end; $i++) {
            $phraseGroups = array_merge($phraseGroups, $this->verse($i));
            if ($i != $end) {
                $phraseGroups[] = "";
            }
        }
        return $phraseGroups;
    }

    public function song(): array
    {
        return $this->verses(1, 8);
    }
}
