<?php

class Position
{
    public $x, $y;

    public function __construct($y, $x)
    {
        $this->x = $x;
        $this->y = $y;
    }
}