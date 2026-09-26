import java.util.*;

class TwoBucket {

    int bucketOneCap, bucketTwoCap, desiredLiters, totalMoves;
    String startBucket;
    List<Bucket> bucketList;
    Set<Bucket> visitedBucketList;
    Result result;

    TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {
        this.bucketOneCap = bucketOneCap;
        this.bucketTwoCap = bucketTwoCap;
        this.desiredLiters = desiredLiters;
        this.startBucket = startBucket;
        this.bucketList = List.of(new Bucket(0, 0, startBucket));
        this.visitedBucketList = new HashSet<>(bucketList);
        this.totalMoves = 0;

        while(bucketList.stream()
                .noneMatch(buck -> buck.bucketOne == desiredLiters ||
                        buck.bucketTwo == desiredLiters)
        ) {
            totalMoves += 1;
            bucketList = bucketList.stream().flatMap(buck ->
                            placeWater(buck).stream()).filter(buck -> !visitedBucketList.contains(buck))
                    .distinct().toList();

            if (bucketList.isEmpty()) {
                throw new UnreachableGoalException();
            }

            visitedBucketList.addAll(bucketList);
        }
        Bucket data = bucketList.stream()
                .filter(buck -> buck.bucketOne == desiredLiters ||
                        buck.bucketTwo == desiredLiters)
                .toList().getFirst();

        this.result = new Result(totalMoves, data.bucketOne == desiredLiters ? "one" : "two", data.bucketOne != desiredLiters ? data.bucketOne : data.bucketTwo);
    }

    static class Bucket {
        int bucketOne;
        int bucketTwo;
        String currBucket;

        Bucket(int bucketOne, int bucketTwo, String currBucket) {
            this.bucketOne = bucketOne;
            this.bucketTwo = bucketTwo;
            this.currBucket = currBucket;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bucket bucket = (Bucket) o;
            return bucketOne == bucket.bucketOne &&
                    bucketTwo == bucket.bucketTwo &&
                    Objects.equals(currBucket, bucket.currBucket);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bucketOne, bucketTwo, currBucket);
        }
    }

    List<Bucket> placeWater(Bucket buck) {
        List<Bucket> buckets = new ArrayList<>();
        if(totalMoves <= 1) {
            switch (startBucket) {
                case "one" -> bucketOneAction(buck, buckets);
                case "two" -> bucketTwoAction(buck, buckets);
            }
        }
        else {
            bucketOneAction(buck, buckets);
            bucketTwoAction(buck, buckets);
        }
        return buckets.stream().filter(bu -> startBucket.equals("one") ?
                !(bu.bucketOne == 0 && bu.bucketTwo == bucketTwoCap) :
                !(bu.bucketTwo == 0 && bu.bucketOne == bucketOneCap))
                .toList();
    }

    void bucketOneAction(Bucket buck, List<Bucket> buckets) {
        if(buck.bucketOne != bucketOneCap) {
            buckets.add(new Bucket(bucketOneCap, buck.bucketTwo, "one"));
        }
        if(buck.bucketOne != 0) {
            buckets.add(new Bucket(0, buck.bucketTwo, "one"));
        }
        if (buck.bucketTwo != bucketTwoCap && buck.bucketOne != 0) {
            int liter = Math.min(buck.bucketOne, bucketTwoCap - buck.bucketTwo);
            buckets.add(new Bucket(buck.bucketOne - liter, buck.bucketTwo + liter, "one"));
        }
    }

    void bucketTwoAction(Bucket buck, List<Bucket> buckets) {
        if(buck.bucketTwo != bucketTwoCap) {
            buckets.add(new Bucket(buck.bucketOne, bucketTwoCap, "two"));
        }
        if(buck.bucketTwo != 0) {
            buckets.add(new Bucket(buck.bucketOne, 0, "two"));
        }
        if (buck.bucketOne != bucketOneCap && buck.bucketTwo != 0) {
            int liter = Math.min(buck.bucketTwo, bucketOneCap - buck.bucketOne);
            buckets.add(new Bucket(buck.bucketOne + liter, buck.bucketTwo - liter, "two"));
        }
    }

    Result getResult() {
        return result;
    }
}
