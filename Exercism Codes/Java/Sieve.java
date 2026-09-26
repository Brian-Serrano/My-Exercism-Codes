import java.util.ArrayList;
import java.util.List;

class Sieve {

    private final int mp;
    Sieve(int maxPrime) {
        mp = maxPrime;
    }

    List<Integer> getPrimes() {
        List<Integer> pr = new ArrayList<>();
        boolean[] prime = new boolean[mp + 1];
        for (int i = 0; i <= mp; i++) prime[i] = true;
        for (int p = 2; p * p <= mp; p++) if (prime[p]) for (int i = p * p; i <= mp; i += p) prime[i] = false;
        for (int i = 2; i <= mp; i++) if (prime[i]) pr.add(i);
        return pr;
    }
}
