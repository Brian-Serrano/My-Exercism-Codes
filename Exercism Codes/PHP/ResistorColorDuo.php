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

class ResistorColorDuo
{
    public function getColorsValue(array $colors): int
    {
        $code = "";
        
        for ($i = 0; $i < 2; $i++) {
            switch ($colors[$i]) {
                case "black":
                    $code .= "0";
                    break;
                case "brown":
                    $code .= "1";
                    break;
                case "red":
                    $code .= "2";
                    break;
                case "orange":
                    $code .= "3";
                    break;
                case "yellow":
                    $code .= "4";
                    break;
                case "green":
                    $code .= "5";
                    break;
                case "blue":
                    $code .= "6";
                    break;
                case "violet":
                    $code .= "7";
                    break;
                case "grey":
                    $code .= "8";
                    break;
                case "white":
                    $code .= "9";
                    break;
            }
        }
        return intval($code);
    }
}