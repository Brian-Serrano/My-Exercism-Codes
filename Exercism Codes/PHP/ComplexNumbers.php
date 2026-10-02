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

class ComplexNumbers
{
    public float $real;
    public float $imaginary;
    
    public function __construct($real = 0, $imaginary = 0)
    {
        $this->real = $real;
        $this->imaginary = $imaginary;
    }

    public function mul(ComplexNumbers $other): ComplexNumbers
    {
        return new ComplexNumbers(
            ($this->real * $other->real) - ($this->imaginary * $other->imaginary),
            ($this->real * $other->imaginary) + ($this->imaginary * $other->real)
        );
    }

    public function add(ComplexNumbers $other): ComplexNumbers
    {
        return new ComplexNumbers($this->real + $other->real, $this->imaginary + $other->imaginary);
    }

    public function sub(ComplexNumbers $other): ComplexNumbers
    {
        return new ComplexNumbers($this->real - $other->real, $this->imaginary - $other->imaginary);
    }

    public function div(ComplexNumbers $other): ComplexNumbers
    {
        $denominator = $other->real * $other->real + $other->imaginary * $other->imaginary;
            return new ComplexNumbers(
                (($this->real * $other->real) + ($this->imaginary * $other->imaginary)) / $denominator,
                (($this->imaginary * $other->real) - ($this->real * $other->imaginary)) / $denominator
            );
    }

    public function abs(): float
    {
        return sqrt($this->real * $this->real + $this->imaginary * $this->imaginary);
    }

    public function conjugate(): ComplexNumbers
    {
        return new ComplexNumbers($this->real, -$this->imaginary);
    }

    public function exp(): ComplexNumbers
    {
        return new ComplexNumbers(exp($this->real) * cos($this->imaginary), exp($this->real) * sin($this->imaginary));
    }
}
