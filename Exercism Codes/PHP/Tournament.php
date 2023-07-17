<?php

declare(strict_types=1);

class Tournament
{
    public function tally(string $scores): string {
        $data = $scores != '' ? array_map(function ($s) {
            return mb_split(";", $s);
        }, mb_split("\n", $scores)) : array();
        return $this->stringData($this->processData($data));
    }

    public function processData(array $data): array {
        $players = array();
        foreach($data as $d) {
            for($i = 0; $i < 2; $i++) {
                $status = $i == 0 ? $d[2] : $this->reverse($d[2]);
                $playerOneIndex = array_search($d[$i], array_map(function($p) {
                    return $p->name;
                }, $players));
                if($playerOneIndex === false) {
                    $players[] = new Player($d[$i], $status);
                }
                else {
                    $players[$playerOneIndex]->update($status);
                }
            }
        }
        usort($players, function($a, $b) {
            if ($a->points === $b->points) {
                return strcmp($a->name, $b->name);
            }
            return $b->points - $a->points;
        });
        return $players;
    }

    public function stringData(array $players): string {
        $line = array("Team                           | MP |  W |  D |  L |  P");
        foreach($players as $p) {
            $line[] = "$p->name".str_repeat(' ', 31 - strlen($p->name))."|  $p->played |  $p->win |  $p->draw |  $p->loss |  $p->points";
        }
        return implode("\n", $line);
    }

    public function reverse(string $status): string {
        return $status == "draw" ? "draw" : ($status == "win" ? "loss" : "win");
    }
}

class Player
{
    public string $name;
    public int $played;
    public int $win;
    public int $draw;
    public int $loss;
    public int $points;

    public function __construct(string $name, string $value) {
        $this->name = $name;
        $this->win = 0;
        $this->loss = 0;
        $this->draw = 0;
        $this->update($value);
        $this->compute();
    }

    public function update(string $value) {
        if($value == "win") $this->win++;
        if($value == "loss") $this->loss++;
        if($value == "draw") $this->draw++;
        $this->compute();
    }

    public function compute() {
        $this->played = $this->win + $this->loss + $this->draw;
        $this->points = ($this->win * 3) + $this->draw;
    }
}