import re
import string


class SgfTree:
    def __init__(self, properties=None, children=None):
        self.properties = properties or {}
        self.children = children or []

    def __repr__(self, indent=None):
        return f"({self.properties}: {self.children})"

    def __eq__(self, other):
        if not isinstance(other, SgfTree):
            return False
        for key, value in self.properties.items():
            if key not in other.properties:
                return False
            if other.properties[key] != value:
                return False
        for key in other.properties.keys():
            if key not in self.properties:
                return False
        if len(self.children) != len(other.children):
            return False
        for child, other_child in zip(self.children, other.children):
            if child != other_child:
                return False
        return True

    def __ne__(self, other):
        return not self == other

class Property:
    def __init__(self, kind, label):
        self.kind = kind
        self.label = label


def parse(input_string: str) -> SgfTree:
    if not input_string or not input_string[0] == "(" or not input_string[-1] == ")":
        raise ValueError("tree missing")
    sgf = construct_node(None, input_string.replace("\\[", "[").replace("\\]", "]")[1:-1])
    if not sgf:
        raise ValueError("tree with no nodes")
    return sgf

def construct_node(node: SgfTree, input_string: str) -> SgfTree:
    matcher = get_node(input_string)
    parenthesis = parse_parenthesis(input_string)
    if matcher:
        node = parse_node(matcher)
        child_node = construct_node(node, input_string.replace(matcher, ""))
        if child_node:
            node.children.append(child_node)
        return node
    if parenthesis:
        for par in parenthesis:
            extract_par = par[1:-1]
            child_node = construct_node(parse_node(extract_par), extract_par)
            if child_node:
                node.children.append(child_node)
    return None

def get_node(text):
    if len(text) > 0 and text[0] != ";":
        return None

    for i in range(len(text)):
        if 0 < i < len(text) and text[i - 1:i + 1] == "(;":
            return text[:i - 1]
        if 0 < i < len(text) and text[i - 1:i + 1] == "];":
            return text[:i]

    return text


def parse_parenthesis(input_string: str) -> list[str]:
    nodes = []

    while input_string:
        if not input_string.startswith("(;"):
            break

        counter = 1

        for i in range(2, len(input_string)):
            if input_string.startswith("(;", i):
                counter += 1
            elif input_string[i] == ")":
                counter -= 1

            if counter == 0:
                nodes.append(input_string[:i + 1])
                input_string = input_string[i + 1:]
                break
        else:
            break

    return nodes

def parse_node(input_string: str) -> SgfTree:
    properties = {}
    matcher = get_matches(input_string[1:])
    key = None
    for match in matcher:
        if match.kind == "key":
            if match.label.isupper():
                key = match.label
            else:
                raise ValueError("property must be in uppercase")
        if match.kind == "value":
            if key in properties:
                properties[key].append(map_property_value(match.label))
            else:
                properties[key] = [map_property_value(match.label)]

    if key and key not in properties:
        raise ValueError("properties without delimiter")

    return SgfTree(properties)

def map_property_value(c: str) -> str:
    text = list(c[1:-1])
    i = 0

    while i < len(text):
        if i > 0 and (text[i] == "\\" or text[i] == "t" or text[i] == "n") and text[i - 1] == "\\":
            text.pop(i - 1)
            continue
        if i > 0 and text[i] == "\t" and text[i - 1] == "\\":
            text.pop(i - 1)
            text.pop(i - 1)
            text.insert(i - 1, " ")
            continue
        if i > 0 and text[i] == "\n" and text[i - 1] == "\\":
            text.pop(i - 1)
            text.pop(i - 1)
            continue
        i += 1
    return re.sub("\\t", " ", "".join(text))

def get_matches(text):
    result: list[Property] = []
    start = None

    for i, char in enumerate(text):
        if char in string.ascii_letters:
            if i == 0:
                start = i
                continue

            if i > 0 and text[i - 1] == ']' and text[start] == '[':
                if not has_closing_before_opening(text, i):
                    result.append(Property("value", text[start:i]))
                    start = i
                    continue

        if char == '[':
            if i > 0 and text[i - 1] == ']' and text[start] == '[':
                result.append(Property("value", text[start:i]))
                start = i
                continue

            if i > 0 and text[i - 1] in string.ascii_letters and text[start] in string.ascii_letters:
                result.append(Property("key", text[start:i]))
                start = i
                continue

    if len(text) > 0 and text[-1] == ']':
        result.append(Property("value", text[start:]))
    if 1 <= len(text) <= 2 and text.isalpha():
        result.append(Property("key", text))

    return result

def has_closing_before_opening(text, i):
    for x in range(i, len(text)):
        if text[x] == ']':
            return True
        if text[x] == '[':
            return False
    return False