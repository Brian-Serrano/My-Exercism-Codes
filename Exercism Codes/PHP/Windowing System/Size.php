<?php

class Size
{
    public $width, $height;

    public function __construct($height, $width)
    {
        $this->width = $width;
        $this->height = $height;
    }
}