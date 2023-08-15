import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Poker {

    private final List<String> hands;
    private final Map<String, Integer> rankValues;

    public Poker(List<String> hands) {
        this.hands = hands;
        rankValues = Map.ofEntries(
                Map.entry("2", 2),
                Map.entry("3", 3),
                Map.entry("4", 4),
                Map.entry("5", 5),
                Map.entry("6", 6),
                Map.entry("7", 7),
                Map.entry("8", 8),
                Map.entry("9", 9),
                Map.entry("10", 10),
                Map.entry("J", 11),
                Map.entry("Q", 12),
                Map.entry("K", 13),
                Map.entry("A", 14)
        );
    }

    public List<String> getBestHands() {
        List<Hand> handInfo = new ArrayList<>();

        for (String s : hands) {
            Tuple<List<Integer>, Boolean> straightFlush = isStraightFlush(s);
            Tuple<List<Integer>, Boolean> square = isSquare(s);
            Tuple<List<Integer>, Boolean> fullHouse = isFullHouse(s);
            Tuple<List<Integer>, Boolean> flush = isFlush(s);
            Tuple<List<Integer>, Boolean> straight = isStraight(s);
            Tuple<List<Integer>, Boolean> threeOfKind = isThreeOfKind(s);
            Tuple<List<Integer>, Boolean> twoPair = isTwoPair(s);
            Tuple<List<Integer>, Boolean> onePair = isOnePair(s);

            if (straightFlush.item2) {
                handInfo.add(new Hand(s, 9, straightFlush.item1));
            } else if (square.item2) {
                handInfo.add(new Hand(s, 8, square.item1));
            } else if (fullHouse.item2) {
                handInfo.add(new Hand(s, 7, fullHouse.item1));
            } else if (flush.item2) {
                handInfo.add(new Hand(s, 6, flush.item1));
            } else if (straight.item2) {
                handInfo.add(new Hand(s, 5, straight.item1));
            } else if (threeOfKind.item2) {
                handInfo.add(new Hand(s, 4, threeOfKind.item1));
            } else if (twoPair.item2) {
                handInfo.add(new Hand(s, 3, twoPair.item1));
            } else if (onePair.item2) {
                handInfo.add(new Hand(s, 2, onePair.item1));
            } else {
                handInfo.add(new Hand(s, 1, getRank(s)));
            }
        }
        int max = handInfo.get(0).category;
        for (Hand hand : handInfo) {
            if (hand.category > max) {
                max = hand.category;
            }
        }
        final int maximum = max;
        List<Hand> maxHands = handInfo.stream().filter(h -> h.category == maximum).toList();
        if(maxHands.size() > 1) {
            List<String> hand = new ArrayList<>();
            List<List<Integer>> ranks = new ArrayList<>();
            for (Hand maxHand : maxHands) {
                hand.add(maxHand.hand);
                ranks.add(maxHand.ranks);
            }
            return highest(hand, ranks);
        }
        return maxHands.stream().map(h -> h.hand).toList();
    }

    private Tuple<List<Integer>, Boolean> isStraightFlush(String hand) {
        List<Integer> rank = getRank(hand);
        List<Character> suit = getSuit(hand);
        checkAce(rank);
        return new Tuple<>(
                Collections.singletonList(rank.get(rank.size() - 1)),
                suit.stream().distinct().count() == 1 && IntStream.range(1, 5).allMatch(n -> rank.get(n) + 1 == rank.get(n - 1))
        );
    }

    private Tuple<List<Integer>, Boolean> isSquare(String hand) {
        List<Integer> rank = getRank(hand);
        int first = rank.get(0);
        int last = rank.get(rank.size() - 1);
        int num1 = Collections.frequency(rank, first);
        int num2 = Collections.frequency(rank, last);
        return new Tuple<>(
                Arrays.asList(num1 == 4 ? first : last, num1 == 4 ? last : first),
                num2 == 4 || num1 == 4
        );
    }

    private Tuple<List<Integer>, Boolean> isFullHouse(String hand) {
        List<Integer> rank = getRank(hand);
        int first = rank.get(0);
        int last = rank.get(rank.size() - 1);
        int num1 = Collections.frequency(rank, first);
        int num2 = Collections.frequency(rank, last);
        return new Tuple<>(
                Arrays.asList(num1 == 3 ? first : last, num1 == 3 ? last : first),
                Set.of(2, 3).equals(new HashSet<>(Arrays.asList(num2, num1)))
        );
    }

    private Tuple<List<Integer>, Boolean> isFlush(String hand) {
        List<Character> suit = getSuit(hand);
        return new Tuple<>(getRank(hand), suit.stream().distinct().count() == 1);
    }

    private Tuple<List<Integer>, Boolean> isStraight(String hand) {
        List<Integer> rank = getRank(hand);
        checkAce(rank);
        return new Tuple<>(
                Collections.singletonList(rank.get(rank.size() - 1)),
                IntStream.range(1, 5).allMatch(n -> rank.get(n) + 1 == rank.get(n - 1))
        );
    }

    private Tuple<List<Integer>, Boolean> isThreeOfKind(String hand) {
        List<Integer> rank = getRank(hand);
        int middle = rank.get(rank.size() / 2);
        int num1 = Collections.frequency(rank, middle);
        List<Integer> result = new ArrayList<>();
        result.add(middle);
        result.addAll(rank.stream().filter(c -> Collections.frequency(rank, c) == 1).toList());
        return new Tuple<>(result, num1 == 3);
    }

    private Tuple<List<Integer>, Boolean> isTwoPair(String hand) {
        List<Integer> rank = getRank(hand);
        int second = rank.get(1);
        int fourth = rank.get(3);
        int num1 = Collections.frequency(rank, second);
        int num2 = Collections.frequency(rank, fourth);
        List<Integer> result = new ArrayList<>();
        result.add(second);
        result.add(fourth);
        result.add(rank.stream().filter(c -> Collections.frequency(rank, c) == 1).findFirst().orElse(rank.get(0)));
        return new Tuple<>(result, num1 == 2 && num2 == 2);
    }

    private Tuple<List<Integer>, Boolean> isOnePair(String hand) {
        List<Integer> rank = getRank(hand);
        List<Integer> pairs = rank.stream().distinct().filter(c -> Collections.frequency(rank, c) == 2).toList();
        List<Integer> result = new ArrayList<>();
        if(!pairs.isEmpty()) result.add(pairs.get(0));
        result.addAll(rank.stream().filter(c -> Collections.frequency(rank, c) == 1).toList());
        return new Tuple<>(result, pairs.size() == 1);
    }

    private List<String> highest(List<String> hands, List<List<Integer>> ranks) {
        List<String> result = new ArrayList<>();
        List<Integer> highest = ranks.get(0);
        for (List<Integer> rank : ranks) {
            if (larger(rank, highest)) {
                highest = rank;
            }
        }
        for(int i = 0; i < ranks.size(); i++) {
            if(highest.equals(ranks.get(i))) {
                result.add(hands.get(i));
            }
        }
        return result;
    }

    private void checkAce(List<Integer> rank) {
        if(IntStream.range(2, 5).allMatch(n -> rank.get(n) + 1 == rank.get(n - 1)) && rank.get(1) == 5 && rank.get(0) == 14) {
            rank.set(0, 1);
            rank.sort(Comparator.reverseOrder());
        }
    }

    private boolean larger(List<Integer> lst1, List<Integer> lst2) {
        for(int i = 0; i < lst1.size(); i++) {
            if(lst1.get(i) > lst2.get(i)) return true;
            if(lst1.get(i) < lst2.get(i)) return false;
        }
        return false;
    }

    private List<Integer> getRank(String hand) {
        return Arrays.stream(hand.split(" ")).map(c -> rankValues.get(c.substring(0, c.length() - 1))).sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Character> getSuit(String hand) {
        return Arrays.stream(hand.split(" ")).map(c -> c.charAt(c.length() - 1)).toList();
    }

    private static class Tuple<T, U> {
        public T item1;
        public U item2;

        private Tuple(T item1, U item2) {
            this.item1 = item1;
            this.item2 = item2;
        }
    }

    private static class Hand {
        public String hand;
        public int category;
        public List<Integer> ranks;

        private Hand(String hand, int category, List<Integer> ranks) {
            this.hand = hand;
            this.category = category;
            this.ranks = ranks;
        }
    }
}
