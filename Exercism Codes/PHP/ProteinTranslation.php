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

class ProteinTranslation
{
    public function getProteins(string $strand)
    {
        $lst = [];
        for ($x = 0; $x < strlen($strand); $x += 3) {
            $s = substr($strand, $x, 3);

            if (preg_match("/[^AUCG]/", $s) || strlen($s) != 3)
                throw new InvalidArgumentException("Invalid codon");
            
            if (in_array($s, ["AUG"]))
                $lst[] = "Methionine";
            else if (in_array($s, ["UUU", "UUC"]))
                $lst[] = "Phenylalanine";
            else if (in_array($s, ["UUA", "UUG"]))
                $lst[] = "Leucine";
            else if (in_array($s, ["UCU", "UCC", "UCA", "UCG"]))
                $lst[] = "Serine";
            else if (in_array($s, ["UAU", "UAC"]))
                $lst[] = "Tyrosine";
            else if (in_array($s, ["UGU", "UGC"]))
                $lst[] = "Cysteine";
            else if (in_array($s, ["UGG"]))
                $lst[] = "Tryptophan";
            else if (in_array($s, ["UAA", "UAG", "UGA"]))
                break;
        }
        return $lst;
    }
}
