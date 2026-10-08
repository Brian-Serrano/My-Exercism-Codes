<?php

class LuckyNumbers
{
    public function sumUp(array $digitsOfNumber1, array $digitsOfNumber2): int
    {
        return strval(implode("", $digitsOfNumber1)) + strval(implode("", $digitsOfNumber2));
    }

    public function isPalindrome(int $number): bool
    {
        return strval($number) == strrev(strval($number));
    }

    public function validate(string $input): string
    {
        $input = $input[0] == "-" ? $input : explode("-", $input)[0];
        
        if (strlen($input) == 0)
            return "Required field";
        if (!(is_numeric($input) && (float)$input > 0))
            return "Must be a whole number larger than 0";

        return "";
    }
}
