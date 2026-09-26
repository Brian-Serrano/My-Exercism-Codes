class Bucket:
    def __init__(self, bucket_one, bucket_two, current_bucket):
        self.bucket_one = bucket_one
        self.bucket_two = bucket_two
        self.current_bucket = current_bucket

    def __eq__(self, other):
        return self.bucket_one == other.bucket_one and self.bucket_two == other.bucket_two and self.current_bucket == other.current_bucket

    def __hash__(self):
        return hash((self.bucket_one, self.bucket_two, self.current_bucket))

class TwoBucket:
    def __init__(self, bucket_one, bucket_two, goal, start_bucket):
        self.bucket_one = bucket_one
        self.bucket_two = bucket_two
        self.goal = goal
        self.start_bucket = start_bucket
        self.total_moves = 0
        self.bucket_list = [Bucket(0, 0, self.start_bucket)]
        self.visited_buckets = set(self.bucket_list)

        while not any(buck.bucket_one == goal or buck.bucket_two == goal for buck in self.bucket_list):
            self.total_moves += 1
            self.bucket_list = list(set(x for buck in self.bucket_list for x in self.place_water(buck) if x not in self.visited_buckets))

            if not self.bucket_list:
                raise ValueError("Impossible")

            self.visited_buckets.update(self.bucket_list)

        data = list(filter(lambda buck: buck.bucket_one == goal or buck.bucket_two == goal, self.bucket_list))[0]
        self.other_bucket = data.bucket_one if data.bucket_one != goal else data.bucket_two
        self.final_bucket = "one" if data.bucket_one == goal else "two"

    def place_water(self, buck):
        buckets = []
        if self.total_moves <= 1:
            if self.start_bucket == "one":
                self.bucket_one_action(buck, buckets)
            elif self.start_bucket == "two":
                self.bucket_two_action(buck, buckets)
        else:
            self.bucket_one_action(buck, buckets)
            self.bucket_two_action(buck, buckets)

        return list(filter(lambda bu: self.filter_bucket(bu), buckets))

    def filter_bucket(self, bu):
        bucket_1 = not (bu.bucket_one == 0 and bu.bucket_two == self.bucket_two)
        bucket_2 = not (bu.bucket_two == 0 and bu.bucket_one == self.bucket_one)
        return bucket_1 if self.start_bucket == "one" else bucket_2

    def bucket_one_action(self, buck, buckets):
        if buck.bucket_one != self.bucket_one:
            buckets.append(Bucket(self.bucket_one, buck.bucket_two, "one"))
        if buck.bucket_one != 0:
            buckets.append(Bucket(0, buck.bucket_two, "one"))
        if buck.bucket_two != self.bucket_two and buck.bucket_one != 0:
            liter = min(buck.bucket_one, self.bucket_two - buck.bucket_two)
            buckets.append(Bucket(buck.bucket_one - liter, buck.bucket_two + liter, "one"))

    def bucket_two_action(self, buck, buckets):
        if buck.bucket_two != self.bucket_two:
            buckets.append(Bucket(buck.bucket_one, self.bucket_two, "two"))
        if buck.bucket_two != 0:
            buckets.append(Bucket(buck.bucket_one, 0, "two"))
        if buck.bucket_one != self.bucket_one and buck.bucket_two != 0:
            liter = min(buck.bucket_two, self.bucket_one - buck.bucket_one)
            buckets.append(Bucket(buck.bucket_one + liter, buck.bucket_two - liter, "two"))

def measure(bucket_one, bucket_two, goal, start_bucket):
    bucket = TwoBucket(bucket_one, bucket_two, goal, start_bucket)

    return bucket.total_moves, bucket.final_bucket, bucket.other_bucket
