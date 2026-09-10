def rebase(input_base, digits, output_base):
    if input_base < 2:
        raise ValueError("input base must be >= 2")
    if output_base < 2:
        raise ValueError("output base must be >= 2")

    result = []
    dec = 0
    n = len(digits)

    for idx, d in enumerate(digits):
        if d < 0 or d >= input_base:
            raise ValueError("all digits must satisfy 0 <= d < input base")

        dec += pow(input_base, n - idx - 1) * d

    while dec > 0:
        result.insert(0, dec % output_base)
        dec //= output_base

    return result if result else [0]