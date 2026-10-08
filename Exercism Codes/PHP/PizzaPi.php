<?php

class PizzaPi
{
    public function calculateDoughRequirement(int $pizzas, int $persons)
    {
        return $pizzas * (($persons * 20) + 200);
    }

    public function calculateSauceRequirement(int $pizzas, int $sauceCanVolume)
    {
        return $pizzas * 125 / $sauceCanVolume;
    }

    public function calculateCheeseCubeCoverage(int $cheeseDimension, float $thickness, int $diameter)
    {
        return intval(($cheeseDimension ** 3) / ($thickness * M_PI * $diameter));
    }

    public function calculateLeftOverSlices(int $pizzas, int $friends)
    {
        return ($pizzas * 8) % $friends;
    }
}
