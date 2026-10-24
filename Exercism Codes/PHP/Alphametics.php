<?php

declare(strict_types=1);

class Alphametics
{
    public function solve(string $puzzle): ?array
    {
        $letters = array_values(array_unique(array_filter(str_split($puzzle), fn($x) => ctype_alpha($x))));
        $words = preg_split("/\s?\+\s?|\s==\s/", $puzzle);
        $firstLetters = array_map(fn($x) => $x[0], $words);
        $lastLetters = array_map(fn($x) => $x[-1], $words);
        array_pop($lastLetters);
        $answer = $words[count($words) - 1];
        
        unset($letters[array_search($answer[-1], $letters)]);
        array_unshift($letters, $answer[-1]);

        $lettersToMove = [];

        foreach ($letters as $letter) {
            if ($letter == $answer[-1] || in_array($letter, $lastLetters)) {
                $lettersToMove[] = $letter;
            }
        }

        foreach ($lettersToMove as $letter) {
            unset($letters[array_search($letter, $letters)]);
            array_unshift($letters, $letter);
        }

        $mapping = [];
        $result = $this->backtrack($mapping, $letters, $words, $firstLetters, $lastLetters, $answer, 0);
        return count($result) > 0 ? $result : null;
    }

    private function backtrack(array &$mapping, array $letters, array $words, array $firstLetters, array $lastLetters, string $answer, int $idx): array
    {
        if ($idx == count($letters)) {
            return $mapping;
        }

        $letter = $letters[$idx];
        for ($i = 0; $i < 10; $i++) {
            if (in_array($i, $mapping)) {
                continue;
            }
            if ($i == 0 && in_array($letter, $firstLetters)) {
                continue;
            }

            $mapping[$letter] = $i;

            if ($letter == $answer[-1]) {
                $sum = array_sum(array_map(fn($x) => $mapping[$x], $lastLetters));
                $lastDigit = $sum % 10;

                if ($lastDigit != $i) {
                    unset($mapping[$letter]);
                    continue;
                }
            }

            $newMapping = $this->backtrack($mapping, $letters, $words, $firstLetters, $lastLetters, $answer, $idx + 1);

            if (count($newMapping) > 0 && $this->isValid($mapping, $words)) {
                return $newMapping;
            }

            unset($mapping[$letter]);
        }

        return [];
    }

    private function isValid(array $mapping, array $words): bool
    {
        $numbers = array_map(fn($w) => intval(implode("", array_map(fn($c) => $mapping[$c], str_split($w)))), $words);
        $last = array_pop($numbers);
        return array_sum($numbers) == $last;
    }
}