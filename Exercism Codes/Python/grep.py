import re


def grep(pattern, flags, files):
    flags = flags.split(" ")
    lst = []
    for filename in files:
        line_number = 0
        with open(filename, "r") as file:
            for line in file:
                line_number += 1
                pattern_flags = re.IGNORECASE if "-i" in flags else 0
                if "-x" in flags:
                    match = re.fullmatch(pattern, line.strip("\n"), pattern_flags)
                else:
                    match = re.search(pattern, line.strip("\n"), pattern_flags)
                if match if "-v" not in flags else not match:
                    number = (f"{line_number}:" if "-n" in flags else "")
                    lst.append((filename, number + line))

    if "-l" in flags:
        return "".join(list(dict.fromkeys(f"{c[0]}\n" for c in lst)))

    return "".join(f"{c[0]}:{c[1]}" if len(files) > 1 else c[1] for c in lst)