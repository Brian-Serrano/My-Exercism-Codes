NODE, EDGE, ATTR = range(3)


class Node:
    def __init__(self, name, attrs):
        self.name = name
        self.attrs = attrs

    def __eq__(self, other):
        return self.name == other.name and self.attrs == other.attrs


class Edge:
    def __init__(self, src, dst, attrs):
        self.src = src
        self.dst = dst
        self.attrs = attrs

    def __eq__(self, other):
        return (self.src == other.src and
                self.dst == other.dst and
                self.attrs == other.attrs)


class Graph:
    def __init__(self, data=None):
        if data is None:
            self.nodes = []
            self.edges = []
            self.attrs = {}
            return

        if not isinstance(data, list):
            raise TypeError("Graph data malformed")

        self.nodes = []
        self.edges = []
        self.attrs = {}

        for x in data:
            if not x or x[0] is None:
                raise TypeError("Graph item incomplete")

            try:
                if x[0] == NODE:
                    if not isinstance(x[1], str) or not isinstance(x[2], dict):
                        raise ValueError("Node is malformed")

                    self.nodes.append(Node(x[1], x[2]))
                    continue
                if x[0] == EDGE:
                    if not isinstance(x[1], str) or not isinstance(x[2], str) or not isinstance(x[3], dict):
                        raise ValueError("Edge is malformed")

                    self.edges.append(Edge(x[1], x[2], x[3]))
                    continue
                if x[0] == ATTR:
                    if not isinstance(x[1], str) or not isinstance(x[2], str):
                        raise ValueError("Attribute is malformed")

                    self.attrs[x[1]] = x[2]
                    continue
            except IndexError:
                raise TypeError("Graph item incomplete")

            raise ValueError("Unknown item")