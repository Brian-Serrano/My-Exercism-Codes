#include <string>

namespace log_line {
	std::string message(std::string log) {
		return log.substr(log.find(":") + 2);
	}
	std::string log_level(std::string log) {
		return log.substr(1, log.find("]") - 1);
	}
	std::string reformat(std::string log) {
		return message(log) + " (" + log_level(log) + ")";
	}
}
