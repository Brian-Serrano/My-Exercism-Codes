def add_prefix_un(word):
    return 'un' + word


def make_word_groups(vocab_words):
    for i in range(1, len(vocab_words)):
        vocab_words[i] = vocab_words[0] + vocab_words[i]
    return ' :: '.join(vocab_words)


def remove_suffix_ness(word):
    word = word[0:len(word) - len('ness')]
    return word[:-1] + 'y' if word[-1] == 'i' else word


def adjective_to_verb(sentence, index):
    return sentence[:-1].split()[index] + 'en'
