<?php

declare(strict_types=1);

class RotationalCipher
{
    public function rotate(string $text, int $shift): string
    {
        return implode("", array_map(fn($x) => $this->transform_letter($x, $shift), str_split($text)));
    }

    private function transform_letter(string $x, int $key): string
    {
        return preg_match("/[A-Za-z]/", $x) ? mb_chr($this->is_upper($x) + (((mb_ord($x) - $this->is_upper($x)) + $key) % 26)) : $x;
    }

    private function is_upper(string $x): int
    {
        return preg_match("/[A-Z]/", $x) ? 65 : 97;
    }
}
