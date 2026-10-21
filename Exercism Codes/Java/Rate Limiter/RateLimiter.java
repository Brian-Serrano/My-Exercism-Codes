import java.time.Duration;
import java.util.*;

public class RateLimiter<K> {

    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    private final Map<Long, List<K>> dct;

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;

        dct = new HashMap<>();
    }

    public boolean allow(K clientId) {
        long epochNanos = timeSource.now().getEpochSecond() * 1_000_000_000L + timeSource.now().getNano();
        long key = epochNanos / windowSize.toNanos();
        if (!dct.containsKey(key)) {
            dct.put(key, new ArrayList<>());
        }

        if (dct.get(key).stream().filter(c -> Objects.equals(c, clientId)).count() < limit) {
            dct.get(key).add(clientId);
            return true;
        }
        return false;
    }
}
