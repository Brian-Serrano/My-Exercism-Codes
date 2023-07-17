def round_scores(student_scores):
    return list(map(lambda a: round(a), student_scores))


def count_failed_students(student_scores):
    return len(list(filter(lambda a: a <= 40, student_scores)))


def above_threshold(student_scores, threshold):
    return list(filter(lambda a: a >= threshold, student_scores))


def letter_grades(highest):
    return list(map(lambda a: a * ((highest - 40) // 4) + 41, [0, 1, 2, 3]))


def student_ranking(student_scores, student_names):
    new_list = []
    for index, item in enumerate(student_scores):
        new_list.append(str(index + 1) + '. ' + student_names[index] + ': ' + str(item))
    return new_list


def perfect_score(student_info):
    for i in student_info:
        if i[1] == 100:
            return i
    return []
