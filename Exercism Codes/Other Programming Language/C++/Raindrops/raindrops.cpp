#include "raindrops.h"
#include <string>

namespace raindrops {
	std::string convert(int number) {
		std::string lst;
		if (number % 3 == 0) {
			lst += "Pling";
		}
		if (number % 5 == 0) {
			lst += "Plang";
		}
		if (number % 7 == 0) {
			lst += "Plong";
		}
		return ((lst.length() > 0) ? lst : std::to_string(number));
	}
}
