using Xunit.Internal;

public enum Bucket
{
    One,
    Two
}

public class TwoBucketResult
{
    public int Moves { get; set; }
    public Bucket GoalBucket { get; set; }
    public int OtherBucket { get; set; }
}

public class BucketData
{
    public int bucketOne;
    public int bucketTwo;
    public Bucket currentBucket;

    public BucketData(int bucketOne, int bucketTwo, Bucket currentBucket)
    {
        this.bucketOne = bucketOne;
        this.bucketTwo = bucketTwo;
        this.currentBucket = currentBucket;
    }

    public override bool Equals(object? obj) => obj is BucketData data && bucketOne == data.bucketOne && bucketTwo == data.bucketTwo && currentBucket == data.currentBucket;
    public override int GetHashCode() => HashCode.Combine(bucketOne, bucketTwo, currentBucket);
}

public class TwoBucket
{
    private int bucketOne, bucketTwo, totalMoves;
    private Bucket startBucket;
    private List<BucketData> bucketList;
    private HashSet<BucketData> visitedBuckets;

    public TwoBucket(int bucketOne, int bucketTwo, Bucket startBucket)
    {
        this.bucketOne = bucketOne;
        this.bucketTwo = bucketTwo;
        this.startBucket = startBucket;
        this.totalMoves = 0;
        this.bucketList = [new BucketData(0, 0, this.startBucket)];
        this.visitedBuckets = [.. this.bucketList];
    }

    public TwoBucketResult Measure(int goal)
    {
        while (!this.bucketList.Any(buck => buck.bucketOne == goal || buck.bucketTwo == goal))
        {
            this.totalMoves++;
            this.bucketList = this.bucketList.SelectMany(buck => PlaceWater(buck))
                .Where(x => !this.visitedBuckets.Contains(x)).Distinct().ToList();

            if (this.bucketList.Count == 0)
            {
                throw new ArgumentException();
            }

            this.visitedBuckets.AddRange(this.bucketList);
        }
        BucketData data = this.bucketList.Where(buck => buck.bucketOne == goal || buck.bucketTwo == goal).First();

        return new TwoBucketResult {
            Moves = this.totalMoves,
            GoalBucket = data.bucketOne == goal ? Bucket.One : Bucket.Two,
            OtherBucket = data.bucketOne != goal ? data.bucketOne : data.bucketTwo
        };
    }

    private List<BucketData> PlaceWater(BucketData buck)
    {
        List<BucketData> buckets = new List<BucketData>();
        if (this.totalMoves <= 1)
        {
            if (this.startBucket == Bucket.One)
            {
                BucketOneAction(buck, buckets);
            }
            else if (this.startBucket == Bucket.Two)
            {
                BucketTwoAction(buck, buckets);
            }
        }
        else
        {
            BucketOneAction(buck, buckets);
            BucketTwoAction(buck, buckets);
        }

        return buckets.Where(bu => this.startBucket == Bucket.One ?
                !(bu.bucketOne == 0 && bu.bucketTwo == this.bucketTwo) :
                !(bu.bucketTwo == 0 && bu.bucketOne == this.bucketOne)).ToList();
    }

    private void BucketOneAction(BucketData buck, List<BucketData> buckets)
    {
        if (buck.bucketOne != this.bucketOne)
        {
            buckets.Add(new BucketData(this.bucketOne, buck.bucketTwo, Bucket.One));
        }
        if (buck.bucketOne != 0)
        {
            buckets.Add(new BucketData(0, buck.bucketTwo, Bucket.One));
        }
        if (buck.bucketTwo != this.bucketTwo && buck.bucketOne != 0)
        {
            int liter = Math.Min(buck.bucketOne, this.bucketTwo - buck.bucketTwo);
            buckets.Add(new BucketData(buck.bucketOne - liter, buck.bucketTwo + liter, Bucket.One));
        }
    }

    private void BucketTwoAction(BucketData buck, List<BucketData> buckets)
    {
        if (buck.bucketTwo != this.bucketTwo)
        {
            buckets.Add(new BucketData(buck.bucketOne, this.bucketTwo, Bucket.Two));
        }
        if (buck.bucketTwo != 0)
        {
            buckets.Add(new BucketData(buck.bucketOne, 0, Bucket.Two));
        }
        if (buck.bucketOne != this.bucketOne && buck.bucketTwo != 0)
        {
            int liter = Math.Min(buck.bucketTwo, this.bucketOne - buck.bucketOne);
            buckets.Add(new BucketData(buck.bucketOne + liter, buck.bucketTwo - liter, Bucket.Two));
        }
    }
}
