"""Functions to help edit essay homework using string manipulation."""


def capitalize_title(title):
    return title.title()


def check_sentence_ending(sentence):
    return sentence.endswith('.')


def clean_up_spacing(sentence):
    return sentence.strip(' ')


def replace_word_choice(sentence, old_word, new_word):
    return sentence.replace(old_word, new_word)
