<?php

declare(strict_types=1);

class SimpleCipher
{
    public $key;

    public function __construct(string $key = null)
    {
        if ($key === null) {
            $this->key = implode("", array_map(fn($val): string =>  mb_chr(rand(97, 122)), range(0, 99)));
        }
        else {
            if ($key === "" || preg_match("/[A-Z0-9]/", $key)) {
                throw new InvalidArgumentException();
            }

            $this->key = $key;
        }
    }

    public function encode(string $plainText): string
    {
        $result = [];
        for ($i = 0; $i < strlen($plainText); $i++) {
            $char = (mb_ord($plainText[$i]) - 97) + (mb_ord($this->key[$i % strlen($this->key)]) - 97);
            $result[] = mb_chr(($char % 26) + 97);
        }
        return implode("", $result);
    }

    public function decode(string $cipherText): string
    {
        $result = [];
        for ($i = 0; $i < strlen($cipherText); $i++) {
            $char = (mb_ord($cipherText[$i]) - 97) - (mb_ord($this->key[$i % strlen($this->key)]) - 97);
            $result[] = mb_chr((($char % 26) + ($char < 0 ? 26 : 0)) + 97);
        }
        return implode("", $result);
    }
}