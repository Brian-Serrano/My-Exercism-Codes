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

class Allergies
{
    private int $score;
    
    public function __construct(int $score)
    {
        $this->score = $score % 256;
    }

    public function isAllergicTo(Allergen $allergen): bool
    {
        return in_array($allergen, $this->getList());
    }

    public function getList(): array
    {
        $allergens = [];
        $allergenScores = array_keys(Allergen::allergenList());
        while ($this->score > 0) {
            for ($i = count($allergenScores) - 1; $i >= 0; $i--) {
                if ($this->score >= $allergenScores[$i]) {
                    $allergens[] = Allergen::allergenList()[$allergenScores[$i]];
                    $this->score -= $allergenScores[$i];
                    break;
                }
            }
        }
        return $allergens;
    }
}

class Allergen
{
    public string $allergen;

    public const EGGS = "eggs";
    public const PEANUTS = "peanuts";
    public const SHELLFISH = "shellfish";
    public const STRAWBERRIES = "strawberries";
    public const TOMATOES = "tomatoes";
    public const CHOCOLATE = "chocolate";
    public const POLLEN = "pollen";
    public const CATS = "cats";
    
    public function __construct(string $allergen)
    {
        $this->allergen = $allergen;
    }
    
    public static function allergenList(): array
    {
        return [
            1 => new Allergen(Allergen::EGGS),
            2 => new Allergen(Allergen::PEANUTS),
            4 => new Allergen(Allergen::SHELLFISH),
            8 => new Allergen(Allergen::STRAWBERRIES),
            16 => new Allergen(Allergen::TOMATOES),
            32 => new Allergen(Allergen::CHOCOLATE),
            64 => new Allergen(Allergen::POLLEN),
            128 => new Allergen(Allergen::CATS)
        ];
    }
}
