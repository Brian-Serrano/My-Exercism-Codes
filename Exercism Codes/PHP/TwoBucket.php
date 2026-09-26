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

class TwoBucket
{
    private int $bucketOneCap, $bucketTwoCap, $goal, $totalMoves;
    private string $startBucket;
    private array $bucketList, $visitedBuckets;
    
    public function solve(int $sizeBucketOne, int $sizeBucketTwo, int $goal, string $startBucket)
    {
        $this->bucketOneCap = $sizeBucketOne;
        $this->bucketTwoCap = $sizeBucketTwo;
        $this->goal = $goal;
        $this->startBucket = $startBucket;
        $this->totalMoves = 0;
        $this->bucketList = [new Bucket(0, 0, $this->startBucket)];
        $this->visitedBuckets[$this->bucketList[0]->key()] = true;

        while (!$this->checkBucket($this->bucketList)) {
            $this->totalMoves++;
            $this->bucketList = array_values(array_filter(array_merge(...array_map(fn($buck) => $this->placeWater($buck), $this->bucketList)), fn($x) => !isset($visitedBuckets[$x->key()])));

            if (count($this->bucketList) == 0) {
                throw new Exception();
            }

            foreach ($this->bucketList as $x) {
                $visitedBuckets[$x->key()] = true;
            }
        }

        $data = array_values(array_filter($this->bucketList, fn($buck) => $buck->bucketOne == $this->goal || $buck->bucketTwo == $this->goal))[0];

        return new Solution($this->totalMoves, $data->bucketOne == $this->goal ? "one" : "two", $data->bucketOne != $this->goal ? $data->bucketOne : $data->bucketTwo);
    }

    private function checkBucket(array $buckets): bool
    {
        foreach ($buckets as $buck) {
            if ($buck->bucketOne == $this->goal || $buck->bucketTwo == $this->goal) {
                return true;
            }
        }
        return false;
    }

    private function placeWater(Bucket $buck)
    {
        $buckets = [];
        if ($this->totalMoves <= 1) {
            if ($this->startBucket == "one") {
                $this->bucketOneAction($buck, $buckets);
            }
            else if ($this->startBucket == "two") {
                $this->bucketTwoAction($buck, $buckets);
            }
        }
        else {
            $this->bucketOneAction($buck, $buckets);
            $this->bucketTwoAction($buck, $buckets);
        }

        return array_filter($buckets, function($bu) {
            $bucket1 = !($bu->bucketOne == 0 && $bu->bucketTwo == $this->bucketTwoCap);
            $bucket2 = !($bu->bucketTwo == 0 && $bu->bucketOne == $this->bucketOneCap);
            return $this->startBucket == "one" ? $bucket1 : $bucket2;
        });
    }

    private function bucketOneAction($buck, &$buckets)
    {
        if ($buck->bucketOne != $this->bucketOneCap) {
            $buckets[] = new Bucket($this->bucketOneCap, $buck->bucketTwo, "one");
        }
        if ($buck->bucketOne != 0) {
            $buckets[] = new Bucket(0, $buck->bucketTwo, "one");
        }
        if ($buck->bucketTwo != $this->bucketTwoCap && $buck->bucketOne != 0) {
            $liter = min($buck->bucketOne, $this->bucketTwoCap - $buck->bucketTwo);
            $buckets[] = new Bucket($buck->bucketOne - $liter, $buck->bucketTwo + $liter, "one");
        }
    }

    private function bucketTwoAction($buck, &$buckets)
    {
        if ($buck->bucketTwo != $this->bucketTwoCap) {
            $buckets[] = new Bucket($buck->bucketOne, $this->bucketTwoCap, "two");
        }
        if ($buck->bucketTwo != 0) {
            $buckets[] = new Bucket($buck->bucketOne, 0, "two");
        }
        if ($buck->bucketOne != $this->bucketOneCap && $buck->bucketTwo != 0) {
            $liter = min($buck->bucketTwo, $this->bucketOneCap - $buck->bucketOne);
            $buckets[] = new Bucket($buck->bucketOne + $liter, $buck->bucketTwo - $liter, "two");
        }
    }
}

class Bucket
{
    public int $bucketOne, $bucketTwo;
    public string $currentBucket;

    public function __construct(int $bucketOne, int $bucketTwo, string $currentBucket)
    {
        $this->bucketOne = $bucketOne;
        $this->bucketTwo = $bucketTwo;
        $this->currentBucket = $currentBucket;
    }

    public function key(): string
    {
        return $this->bucketOne.",".$this->bucketTwo.",".$this->currentBucket;
    }
}

class Solution
{
    public int $numberOfActions, $litersLeftInOtherBucket;
    public string $nameOfBucketWithDesiredLiters;
    
    public function __construct(int $numberOfActions, string $nameOfBucketWithDesiredLiters, int $litersLeftInOtherBucket)
    {
        $this->numberOfActions = $numberOfActions;
        $this->nameOfBucketWithDesiredLiters = $nameOfBucketWithDesiredLiters;
        $this->litersLeftInOtherBucket = $litersLeftInOtherBucket;
    }
}