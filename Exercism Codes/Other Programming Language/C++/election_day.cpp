#include <string>
#include <vector>

namespace election {

struct ElectionResult {
    std::string name{};
    int votes{};
};

int vote_count(ElectionResult& er) {
    return er.votes;
}

void increment_vote_count(ElectionResult& er, int votes) {
    er.votes += votes;
}

ElectionResult& determine_result(std::vector<ElectionResult> er) {
    ElectionResult winner = er.at(0);
    for (int i = 0; i < er.size(); i++) {
        if (er.at(i).votes > winner.votes) winner = er.at(i);
    }
    winner.name = "President " + winner.name;
    return winner;
}

}