<?php

class ProgramWindow
{
    public $x, $y, $width, $height;

    public function __construct()
    {
        $this->x = 0;
        $this->y = 0;
        $this->width = 800;
        $this->height = 600;
    }

    public function resize($size)
    {
        $this->width = $size->width;
        $this->height = $size->height;
    }

    public function move($pos)
    {
        $this->x = $pos->x;
        $this->y = $pos->y;
    }
}
