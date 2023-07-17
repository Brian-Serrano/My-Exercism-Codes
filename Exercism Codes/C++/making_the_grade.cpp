#include <array>
#include <string>
#include <vector>

std::vector<int> round_down_scores(std::vector<double> student_scores) {
    std::vector<int> new_scores;
    for (int i = 0; i < student_scores.size(); i++) new_scores.push_back(student_scores[i]);
    return new_scores;
}

int count_failed_students(std::vector<int> student_scores) {
    int count = 0;
    for (int i = 0; i < student_scores.size(); i++) if (student_scores[i] <= 40) count++;
    return count;
}

std::vector<int> above_threshold(std::vector<int> student_scores, int threshold) {
    std::vector<int> best_scores;
    for (int i = 0; i < student_scores.size(); i++) if (student_scores[i] >= threshold) best_scores.push_back(student_scores[i]);
    return best_scores;
}

std::array<int, 4> letter_grades(int highest_score) {
    int multiplier = (highest_score - 40) / 4;
    std::array<int, 4> grades = { 0, 0, 0, 0 };
    for (int i = 0; i < 4; i++) grades[i] = i * multiplier + 41;
    return grades;
}

std::vector<std::string> student_ranking(std::vector<int> student_scores, std::vector<std::string> student_names) {
    std::vector<std::string> ranks;
    for (int i = 0; i < student_scores.size(); i++) ranks.push_back(std::to_string(i + 1) + ". " + student_names[i] + ": " + std::to_string(student_scores[i]));
    return ranks;
}

std::string perfect_score(std::vector<int> student_scores, std::vector<std::string> student_names) {
    for (int i = 0; i < student_scores.size(); i++) if (student_scores[i] == 100) return student_names[i];
    return "";
}