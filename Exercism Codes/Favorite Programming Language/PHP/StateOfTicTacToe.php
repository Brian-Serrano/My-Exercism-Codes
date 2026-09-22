<?php

/*
 * By adding type hints and enabling strict type checking, code can become
 * easier to read, self-documenting and reduce the number of potential bugs.
 * By default, type declarations are non-strict, which means they will attempt
 * to change the original type to match the type specified by the
 * type-declaration.
 *
 * In other words, if you pass a string to a function requiring a float,
 * it will attempt to convert the string value to a float.
 *
 * To enable strict mode, a single declare directive must be placed at the top
 * of the file.
 * This means that the strictness of typing is configured on a per-file basis.
 * This directive not only affects the type declarations of parameters, but also
 * a function's return type.
 *
 * For more info review the Concept on strict type checking in the PHP track
 * <link>.
 *
 * To disable strict typing, comment out the directive below.
 */

declare(strict_types=1);

enum State
{
    case Win;
    case Ongoing;
    case Draw;
}

class StateOfTicTacToe
{
    public function gameState(array $board): State
    {
        $x = $this->getPlayerCount($board, "X");
        $o = $this->getPlayerCount($board, "O");
        $xWins = $this->checkWin($board, "X");
        $oWins = $this->checkWin($board, "O");
        if ($x - $o > 1) {
            throw new RuntimeException("Wrong turn order: X went twice");
        }
        if ($x - $o < 0) {
            throw new RuntimeException("Wrong turn order: O started");
        }
        if (($xWins && $x <= $o) || ($oWins && $x > $o)) {
            throw new RuntimeException("Impossible board: game should have ended after the game was won");
        }
        if ($xWins || $oWins) {
            return State::Win;
        }
        if (!str_contains($board[0], " ") && !str_contains($board[1], " ") && !str_contains($board[2], " ")) {
            return State::Draw;
        }
        return State::Ongoing;
    }

    private function checkWin(array $board, string $p): bool
    {
        $states = [];
        $states[] = $this->checkDiagonalWin($board, $p, -1);
        $states[] = $this->checkDiagonalWin($board, $p, 1);
        for ($i = 0; $i < 3; $i++) {
            $states[] = $this->checkVerticalWin($board, $p, $i);
            $states[] = $this->checkHorizontalWin($board, $p, $i);
        }

        return in_array(true, $states);
    }

    private function checkVerticalWin(array $board, string $p, int $line): bool
    {
        return $board[0][$line] == $p && $board[1][$line] == $p && $board[2][$line] == $p;
    }

    private function checkHorizontalWin(array $board, string $p, int $line): bool
    {
        return $board[$line] == str_repeat($p, 3);
    }

    private function checkDiagonalWin(array $board, string $p, int $direction): bool
    {
        return $board[0][1 + $direction] == $p && $board[1][1] == $p && $board[2][1 - $direction] == $p;
    }

    private function getPlayerCount(array $board, string $p): int
    {
        $cnt = 0;
        foreach ($board as $row) {
            foreach (str_split($row) as $cell) {
                if ($cell == $p) {
                    $cnt++;
                }
            }
        }
        return $cnt;
    }
}
