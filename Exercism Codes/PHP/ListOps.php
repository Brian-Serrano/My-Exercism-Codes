<?php

declare(strict_types=1);

class ListOps
{
    public function append(array $list1, array $list2): array
    {
        return array_merge($list1, $list2);
    }

    public function concat(array $list1, array ...$listn): array
    {
        return array_merge($list1, ...$listn);
    }

    public function filter(callable $predicate, array $list): array
    {
        return array_values(array_filter($list, $predicate));
    }

    public function length(array $list): int
    {
        return count($list);
    }

    public function map(callable $function, array $list): array
    {
        return array_map($function, $list);
    }

    public function foldl(callable $function, array $list, $accumulator)
    {
        for ($i = 0; $i < count($list); $i++) {
            $accumulator = $function($accumulator, $list[$i]);
        }
        return $accumulator;
    }

    public function foldr(callable $function, array $list, $accumulator)
    {
        for ($i = count($list) - 1; $i >= 0; $i--) {
            $accumulator = $function($accumulator, $list[$i]);
        }
        return $accumulator;
    }

    public function reverse(array $list): array
    {
        return array_reverse($list);
    }
}
