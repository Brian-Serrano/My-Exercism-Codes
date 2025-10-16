def is_paired(input_string):
    balance_stack = []

    for c in input_string:
        if c in "([{":
            balance_stack.append(c)
        if c == ')':
            if len(balance_stack) > 0 and balance_stack[-1] == '(':
                balance_stack.pop()
            else:
                return False
        if c == ']':
            if len(balance_stack) > 0 and balance_stack[-1] == '[':
                balance_stack.pop()
            else:
                return False
        if c == '}':
            if len(balance_stack) > 0 and balance_stack[-1] == '{':
                balance_stack.pop()
            else:
                return False

    return len(balance_stack) == 0
