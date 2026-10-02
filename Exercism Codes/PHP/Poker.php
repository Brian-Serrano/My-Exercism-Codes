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

class Poker
{
    public array $bestHands = [];

    private const RANK_VALUES = [
        "2" => 2, "3" => 3, "4" => 4, "5" => 5, "6" => 6, "7" => 7,
        "8" => 8, "9" => 9, "10" => 10, "J" => 11, "Q" => 12, "K" => 13, "A" => 14
    ];

    public function __construct(array $hands)
    {
        $handInfo = [];

        foreach ($hands as $s) {
            $straightFlush = $this->isStraightFlush($s);
            $square = $this->isSquare($s);
            $fullHouse = $this->isFullHouse($s);
            $flush = $this->isFlush($s);
            $straight = $this->isStraight($s);
            $threeOfKind = $this->isThreeOfKind($s);
            $twoPair = $this->isTwoPair($s);
            $onePair = $this->isOnePair($s);

            if ($straightFlush[1])
                $handInfo[] = new Hand($s, 9, $straightFlush[0]);
            else if ($square[1])
                $handInfo[] = new Hand($s, 8, $square[0]);
            else if ($fullHouse[1])
                $handInfo[] = new Hand($s, 7, $fullHouse[0]);
            else if ($flush[1])
                $handInfo[] = new Hand($s, 6, $flush[0]);
            else if ($straight[1])
                $handInfo[] = new Hand($s, 5, $straight[0]);
            else if ($threeOfKind[1])
                $handInfo[] = new Hand($s, 4, $threeOfKind[0]);
            else if ($twoPair[1])
                $handInfo[] = new Hand($s, 3, $twoPair[0]);
            else if ($onePair[1])
                $handInfo[] = new Hand($s, 2, $onePair[0]);
            else
                $handInfo[] = new Hand($s, 1, $this->getRank($s));
        }

        $max = $handInfo[0]->category;
        foreach ($handInfo as $hand) {
            if ($hand->category > $max) {
                $max = $hand->category;
            }
        }
        $maxHands = array_filter($handInfo, function($h) use ($max) {
            return $h->category == $max;
        });
        if (count($maxHands) > 1) {
            $hand = [];
            $ranks = [];
            foreach ($maxHands as $maxHand) {
                $hand[] = $maxHand->hand;
                $ranks[] = $maxHand->ranks;
            }
            $this->bestHands = array_values($this->highest($hand, $ranks));
        }
        else {
            $this->bestHands = array_values(array_map(fn($h) => $h->hand, $maxHands));
        }
    }

    private function isStraightFlush(string $hand): array
    {
        $rank = $this->getRank($hand);
        $suit = $this->getSuit($hand);
        $this->checkAce($rank);
        return [
            [$rank[count($rank) - 1]],
            count(array_unique($suit)) == 1 && array_all(range(1, 4), function($n) use ($rank) {
                return $rank[$n] + 1 == $rank[$n - 1];
            })
        ];
    }

    private function isSquare(string $hand): array
    {
        $rank = $this->getRank($hand);
        $first = $rank[0];
        $last = $rank[count($rank) - 1];
        $num1 = array_count_values($rank)[$first];
        $num2 = array_count_values($rank)[$last];
        return [
            [$num1 == 4 ? $first : $last, $num1 == 4 ? $last : $first],
            $num2 == 4 || $num1 == 4
        ];
    }

    private function isFullHouse(string $hand): array
    {
        $rank = $this->getRank($hand);
        $first = $rank[0];
        $last = $rank[count($rank) - 1];
        $num1 = array_count_values($rank)[$first];
        $num2 = array_count_values($rank)[$last];
        $result = [$num1 == 3 ? $first : $last, $num1 == 3 ? $last : $first];
        return [$result, [3, 2] == [$num1 == 3 ? $num1 : $num2, $num1 == 3 ? $num2 : $num1]];
    }

    private function isFlush(string $hand): array
    {
        $suit = $this->getSuit($hand);
        return [$this->getRank($hand), count(array_unique($suit)) == 1];
    }

    private function isStraight(string $hand): array
    {
        $rank = $this->getRank($hand);
        $this->checkAce($rank);
        return [
            [$rank[count($rank) - 1]],
            array_all(range(1, 4), function($n) use ($rank) {
                return $rank[$n] + 1 == $rank[$n - 1];
            })
        ];
    }

    private function isThreeOfKind(string $hand): array
    {
        $rank = $this->getRank($hand);
        $middle = $rank[intval(floor(count($rank) / 2))];
        $num1 = array_count_values($rank)[$middle];
        $result = [];
        $result[] = $middle;
        $result = array_merge($result, array_filter($rank, function($c) use ($rank) {
            return array_count_values($rank)[$c] == 1;
        }));
        return [$result, $num1 == 3];
    }

    private function isTwoPair(string $hand): array
    {
        $rank = $this->getRank($hand);
        $second = $rank[1];
        $fourth = $rank[3];
        $num1 = array_count_values($rank)[$second];
        $num2 = array_count_values($rank)[$fourth];
        $result = [];
        array_push($result, $second, $fourth);
        array_push($result, array_values(array_filter($rank, function($c) use ($rank) {
            return array_count_values($rank)[$c] == 1;
        }))[0] ?? $rank[0]);
        return [$result, $num1 == 2 && $num2 == 2];
    }

    private function isOnePair(string $hand): array
    {
        $rank = $this->getRank($hand);
        $pairs = array_values(array_filter(array_unique($rank), function($c) use ($rank) {
            return array_count_values($rank)[$c] == 2;
        }));
        $result = [];
        if (count($pairs) > 0)
            $result[] = $pairs[0];
        $result = array_merge($result, array_filter($rank, function($c) use ($rank) {
            return array_count_values($rank)[$c] == 1;
        }));
        return [$result, count($pairs) == 1];
    }

    private function highest(array $hands, array $ranks): array
    {
        $result = [];
        $highest = $ranks[0];
        foreach ($ranks as $rank) {
            if ($this->larger($rank, $highest)) {
                $highest = $rank;
            }
        }
        for ($i = 0; $i < count($ranks); $i++) {
            if ($highest == $ranks[$i]) {
                $result[] = $hands[$i];
            }
        }
        return $result;
    }

    private function checkAce(array &$rank)
    {
        if (array_all(range(2, 4), function($n) use ($rank) {
            return $rank[$n] + 1 == $rank[$n - 1];
        }) && $rank[1] == 5 && $rank[0] == 14) {
            $rank[0] = 1;
            rsort($rank);
        }
    }

    private function larger(array $lst1, array $lst2): bool
    {
        for ($i = 0; $i < count($lst1); $i++) {
            if ($lst1[$i] > $lst2[$i])
                return true;
            if ($lst1[$i] < $lst2[$i])
                return false;
        }
        return false;
    }

    private function getRank(string $hand): array
    {
        $ranks = array_map(function($c) {
            return $this::RANK_VALUES[substr($c, 0, strlen($c) - 1)];
        }, explode(",", $hand));
        rsort($ranks);
        return $ranks;
    }

    private function getSuit(string $hand): array
    {
        return array_map(function($c) {
            return $c[strlen($c) - 1];
        }, explode(",", $hand));
    }
}

class Hand
{
    public string $hand;
    public int $category;
    public array $ranks;

    public function __construct(string $hand, int $category, array $ranks)
    {
        $this->hand = $hand;
        $this->category = $category;
        $this->ranks = $ranks;
    }
}
