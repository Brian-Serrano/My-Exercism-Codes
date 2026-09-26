<?php

declare(strict_types=1);

class Sublist
{
    public function compare(array $listOne, array $listTwo): string
    {
        if ($listOne === $listTwo) {
            return "EQUAL";
        }
        if (count($listOne) > count($listTwo) && $this->sublist($listOne, $listTwo)) {
            return "SUPERLIST";
        }
        if (count($listTwo) > count($listOne) && $this->sublist($listTwo, $listOne)) {
            return "SUBLIST";
        }
        return "UNEQUAL";
    }

    private function sublist(array $listOne, array $listTwo): bool
    {
        for ($i = 0; $i <= count($listOne) - count($listTwo); $i++) {
            if (array_slice($listOne, $i, count($listTwo)) === $listTwo) {
                return true;
            }
        }
        return false;
    }
}
