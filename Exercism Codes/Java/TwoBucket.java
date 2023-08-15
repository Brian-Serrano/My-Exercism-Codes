import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class TwoBucket {

    int totalMoves, otherBucket, bucketOneCap, bucketTwoCap, desiredLiters;
    String finalBucket, startBucket;
    List<Bucket> bucketList;

    TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {
        this.bucketOneCap = bucketOneCap;
        this.bucketTwoCap = bucketTwoCap;
        this.desiredLiters = desiredLiters;
        this.totalMoves = 0;
        this.startBucket = startBucket;
        this.bucketList = Arrays.asList(new Bucket(0, 0, startBucket));
        while(bucketList.stream()
                .noneMatch(buck -> buck.bucketOne == desiredLiters ||
                        buck.bucketTwo == desiredLiters)
        ) {
            totalMoves += 1;
            bucketList = bucketList.stream().flatMap(buck ->
                            placeWater(buck).stream())
                    .distinct().toList();
        }
        Bucket data = bucketList.stream()
                .filter(buck -> buck.bucketOne == desiredLiters ||
                        buck.bucketTwo == desiredLiters)
                .toList().get(0);
        this.otherBucket = data.bucketOne != desiredLiters ? data.bucketOne : data.bucketTwo;
        this.finalBucket = data.currBucket;
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

    int getTotalMoves() {
        return totalMoves;
    }

    String getFinalBucket() {
        return finalBucket;
    }

    int getOtherBucket() {
        return otherBucket;
    }
}
