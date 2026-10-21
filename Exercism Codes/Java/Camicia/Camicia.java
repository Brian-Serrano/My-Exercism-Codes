import java.util.*;

public class Camicia {

    static CamiciaResult simulateGame(List<String> playerA, List<String> playerB) {
        List<Integer> handA = new ArrayList<>(playerA.stream().map(Camicia::getValue).toList());
        List<Integer> handB = new ArrayList<>(playerB.stream().map(Camicia::getValue).toList());
        String turn = "A";
        List<Integer> pile = new ArrayList<>();
        Set<State> seen = new HashSet<>();
        int totalTricks = 0;
        int cardsPlayed = 0;
        int currentDebt = 0;

        while (true) {
            if (pile.isEmpty()) {
                State state = new State(handA, handB, turn);
                if (seen.contains(state)) {
                    return new CamiciaResult("loop", cardsPlayed, totalTricks);
                }
                seen.add(state);
            }

            List<Integer> activeHand = turn.equals("A") ? handA : handB;
            List<Integer> otherHand = turn.equals("A") ? handB : handA;

            if (activeHand.isEmpty()) {
                int extraTrick = pile.isEmpty() ? 0 : 1;
                return new CamiciaResult("finished", cardsPlayed, totalTricks + extraTrick);
            }

            int cardVal = activeHand.removeFirst();
            pile.add(cardVal);
            cardsPlayed++;

            if (cardVal > 0) {
                currentDebt = cardVal;
                turn = turn.equals("A") ? "B" : "A";
            }
            else {
                if (currentDebt > 0) {
                    currentDebt -= 1;
                    if (currentDebt == 0) {
                        otherHand.addAll(pile);
                        pile.clear();
                        totalTricks++;

                        if (handA.isEmpty() || handB.isEmpty()) {
                            return new CamiciaResult("finished", cardsPlayed, totalTricks);
                        }
                        turn = turn.equals("A") ? "B" : "A";
                    }
                }
                else {
                    turn = turn.equals("A") ? "B" : "A";
                }
            }
        }
    }

    static int getValue(String card) {
        return switch (card) {
            case "J" -> 1;
            case "Q" -> 2;
            case "K" -> 3;
            case "A" -> 4;
            default -> 0;
        };
    }

    static class State {
        public List<Integer> handA, handB;
        public String turn;

        public State(List<Integer> handA, List<Integer> handB, String turn) {
            this.handA = handA;
            this.handB = handB;
            this.turn = turn;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return handA.equals(state.handA) && handB.equals(state.handB) && turn.equals(state.turn);
        }

        @Override
        public int hashCode() {
            return Objects.hash(handA, handB, turn);
        }
    }
}
