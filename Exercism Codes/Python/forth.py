import re


class StackUnderflowError(Exception):
    pass


def evaluate(input_data):
    tokens = []
    func = []
    func_name = []

    for instruction in input_data:
        f: list[str] = re.findall("[^ :;]+", instruction.lower())
        if re.fullmatch("(^:.*;$)", instruction):
            if is_number(f[0]):
                raise ValueError("illegal operation")
            func_name.insert(0, f[0])
            func.insert(0, f[1:])
        else:
            tokens.extend(f)
    return call([], tokens, func, func_name, 0)

def call(stack: list, tokens, func, func_name: list, where):
    for token in tokens:
        index = func_name.index(token) if token in func_name else -1
        if index != -1:
            call(stack, func[where:][index], func, func_name, index + 1)
            continue
        if is_number(token):
            stack.append(int(token))
            continue
        if token == "+":
            if len(stack) < 2:
                raise StackUnderflowError("Insufficient number of items in stack")
            stack.append(stack.pop() + stack.pop())
            continue
        if token == "-":
            if len(stack) < 2:
                raise StackUnderflowError("Insufficient number of items in stack")
            first = stack.pop()
            second = stack.pop()
            stack.append(second - first)
            continue
        if token == "*":
            if len(stack) < 2:
                raise StackUnderflowError("Insufficient number of items in stack")
            stack.append(stack.pop() * stack.pop())
            continue
        if token == "/":
            if len(stack) < 2:
                raise StackUnderflowError("Insufficient number of items in stack")
            first = stack.pop()
            if first == 0:
                raise ZeroDivisionError("divide by zero")
            second = stack.pop()
            stack.append(second // first)
            continue
        if token == "dup":
            if len(stack) < 1:
                raise StackUnderflowError("Insufficient number of items in stack")
            stack.append(stack[-1])
            continue
        if token == "drop":
            if len(stack) < 1:
                raise StackUnderflowError("Insufficient number of items in stack")
            stack.pop()
            continue
        if token == "swap":
            if len(stack) < 2:
                raise StackUnderflowError("Insufficient number of items in stack")
            first = stack.pop()
            second = stack.pop()
            stack.append(first)
            stack.append(second)
            continue
        if token == "over":
            if len(stack) < 2:
                raise StackUnderflowError("Insufficient number of items in stack")
            stack.append(stack[-2])
            continue
        raise ValueError("undefined operation")
    return stack

def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        return False