#include "triangle.h"
#include <stdexcept>

namespace triangle {
	flavor kind(double a, double b, double c) {
		if (a + b < c || a + c < b || b + c < a || a <= 0 || b <= 0 || c <= 0) throw std::domain_error("");
		if (a == b && b == c && a == c) return flavor::equilateral;
		if (a == b || b == c || a == c) return flavor::isosceles;
		return flavor::scalene;
	}
}
