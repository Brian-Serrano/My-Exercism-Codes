<?php

declare(strict_types=1);

class ResistorColorTrio
{
    private $colors = ["black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"];

    public function label($col): string
    {
        $result = intval(array_search($col[0], $this->colors) . array_search($col[1], $this->colors) . str_repeat("0", array_search($col[2], $this->colors)));
        if ($result >= 1000000000) {
            return ($result / 1000000000) . " gigaohms";
        }
        else if ($result >= 1000000) {
            return ($result / 1000000) . " megaohms";
        }
        else if ($result >= 1000) {
            return ($result / 1000) . " kiloohms";
        }
        else {
            return $result . " ohms";
        }
    }
}