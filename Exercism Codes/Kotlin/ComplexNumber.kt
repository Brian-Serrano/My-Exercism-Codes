import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {

    operator fun plus(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(real + other.real, imag + other.imag)
    }

    operator fun minus(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(real - other.real, imag - other.imag)
    }

    operator fun times(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(
            (real * other.real) - (imag * other.imag),
            (real * other.imag) + (imag * other.real)
        )
    }

    operator fun div(other: ComplexNumber): ComplexNumber {
        val denominator = other.real * other.real + other.imag * other.imag
        return ComplexNumber(
            ((real * other.real) + (imag * other.imag)) / denominator,
            ((imag * other.real) - (real * other.imag)) / denominator
        )
    }

    val abs: Double get() = sqrt(real * real + imag * imag)

    fun conjugate(): ComplexNumber {
        return ComplexNumber(real, -imag)
    }
}

fun exponential(c: ComplexNumber): ComplexNumber {
    return ComplexNumber(exp(c.real) * cos(c.imag), exp(c.real) * sin(c.imag))
}