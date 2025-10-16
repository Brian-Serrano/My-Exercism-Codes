<?php

declare(strict_types=1);

class IsbnVerifier
{
    public function isValid(string $isbn): bool
    {
        $isbn_10 = preg_replace("/-/", "", $isbn);
        $n = strlen($isbn_10);

        if ($n !== 10) {
            return false;
        }

        $total = 0;

        foreach (str_split($isbn_10) as $idx => $digit) {
            $m = $n - $idx;
            if ($idx === $n - 1 && $digit === "X") {
                $total += 10 * $m;
                continue;
            }

            if (preg_match("/\d/", $digit)) {
                $total += intval($digit) * $m;
            }
            else {
                return false;
            }
        }
        return $total % 11 === 0;
    }
}
