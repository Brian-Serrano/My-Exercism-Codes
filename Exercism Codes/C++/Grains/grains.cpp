#include "grains.h"

namespace grains {
	unsigned long long square(int number) {
		return static_cast<unsigned long long>(1) << (number - 1);
	}
	unsigned long long total() {
		unsigned long long count = 0;
		for (int i = 0; i < 64; ++i) count += square(i);
		return count;
	}
}
