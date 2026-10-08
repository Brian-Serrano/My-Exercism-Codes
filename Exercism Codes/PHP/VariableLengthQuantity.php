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

function vlq_encode(array $input): array
{
    $result = [];
    for ($i = 0; $i < count($input); $i++) {
        if ($input[$i] == 0) {
            $result[] = 0;
            continue;
        }

        $res = [];
        while ($input[$i] > 0) {
            $res[] = $input[$i] & 0x7F;
            $input[$i] >>= 7;
        }
        for ($j = 1; $j < count($res); $j++) {
            $res[$j] |= 0x80;
        }
        $result = array_merge($result, array_reverse($res));
    }
    return $result;
}

function vlq_decode(array $input): array
{
    if (($input[count($input) - 1] & 0x80) != 0)
        throw new Exception();
    
    $result = [];
    $c = 0;
    $result[] = 0;
    for ($i = 0; $i < count($input); $i++) {
        $result[$c] = ($result[$c] << 7) | ($input[$i] & 0x7F);
        if (($input[$i] & 0x80) == 0 && count($input) - 1 != $i) {
            $result[] = 0;
            $c++;
        }
    }
    return $result;
}
