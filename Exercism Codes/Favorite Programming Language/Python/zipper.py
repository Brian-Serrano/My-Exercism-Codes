class Zipper:
    @staticmethod
    def from_tree(tree):
        return Zipper(tree, None)

    def __init__(self, tree, up):
        self.v = tree["value"]
        self.u = up
        self.l = None
        self.r = None

        if tree["left"]:
            self.l = Zipper(tree["left"], self)

        if tree["right"]:
            self.r = Zipper(tree["right"], self)

    def value(self):
        return self.v

    def set_value(self, value):
        self.v = value

        return self

    def left(self):
        return self.l

    def set_left(self, left_child):
        if left_child:
            self.l = Zipper(left_child, self)
        else:
            self.l = None

        return self

    def right(self):
        return self.r

    def set_right(self, right_child):
        if right_child:
            self.r = Zipper(right_child, self)
        else:
            self.r = None

        return self

    def up(self):
        return self.u

    def to_tree(self):
        root = self
        while root.u is not None:
            root = root.u

        return self.gen_tree(root)

    def gen_tree(self, child):
        tree = {"value": child.v, "left": None, "right": None}

        if child.l:
            tree["left"] = self.gen_tree(child.l)

        if child.r:
            tree["right"] = self.gen_tree(child.r)

        return tree