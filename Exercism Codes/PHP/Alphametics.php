<?php

declare(strict_types=1);

class Alphametics
{
    private function permutations(array $items, int $r = null): Generator {
        $n = count($items);
        $r = $r ?? $n;

        if ($r > $n || $r < 0) {
            return;
        }

        $indices = range(0, $n - 1);
        $cycles = range($n, $n - $r + 1, -1);

        yield array_slice($items, 0, $r);

        while ($n) {
            $i = $r - 1;
            while ($i >= 0) {
                $cycles[$i]--;
                if ($cycles[$i] == 0) {
                    $rest = array_splice($indices, $i);
                    array_push($rest, array_shift($rest));
                    array_splice($indices, $i, 0, $rest);
                    $cycles[$i] = $n - $i;
                    $i--;
                } else {
                    $j = $cycles[$i];
                    $k = $n - $j;
                    [$indices[$i], $indices[$k]] = [$indices[$k], $indices[$i]];
                    $result = [];
                    for ($x = 0; $x < $r; $x++) {
                        $result[] = $items[$indices[$x]];
                    }
                    yield $result;
                    break;
                }
            }

            if ($i < 0) {
                return;
            }
        }
    }
    public function solve(string $puzzle): ?array
    {
        $letters_set = [];
        $beginning_letters = [];
        foreach (str_split($puzzle) as $idx => $letter) {
            if (preg_match("/[A-Za-z]/", $letter)) {
                if (in_array($puzzle[$idx - 1], [" ", "+"]) || $idx === 0) {
                    $beginning_letters[$letter] = 1;
                }
                $letters_set[$letter] = 1;
            }
        }

        $letters = array_keys($letters_set);
        $leading_letters = array_keys($beginning_letters);
        preg_match_all('/[A-Z]+/', $puzzle, $matches);
        $words = $matches[0];

        foreach ($this->permutations(range(0, 9), count($letters)) as $x) {
            $mapping = array_combine($letters, $x);

            foreach ($leading_letters as $letter) {
                if ($mapping[$letter] === 0) continue 2;
            }

            $maxLeftLen = max(array_map('strlen', array_slice($words, 0, -1)));
            $rightLen = strlen(end($words));
            if ($rightLen < $maxLeftLen) continue;

            $numeric_expr = strtr($puzzle, array_map('strval', $mapping));
            $parts = preg_split("/\s?\+\s?|\s==\s/", $numeric_expr);
            $left_side = array_slice($parts, 0, -1);
            $right_side = (int)end($parts);

            if (array_sum(array_map('intval', $left_side)) === $right_side) {
                return $mapping;
            }
        }
        return null;
    }
}