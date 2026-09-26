def proverb(*input_data, qualifier):
    sentence = []
    for i in range(len(input_data)):
        if i < len(input_data) - 1:
            sentence.append("For want of a " + input_data[i] + " the " + input_data[i + 1] + " was lost.")
        else:
            sentence.append( "And all for the want of a " + (f"{qualifier} " if qualifier else "") + input_data[0] + ".")

    return sentence