class BowlingGame {
    private int score = 0;
    private int numOfThrows = 0;
    private int numOfGame = 0;
    private int gameScore = 0;
    private int bonus = 0;
    public void roll(int pins) {
        if (pins < 0) throw new IllegalStateException("Negative roll is invalid");
        if (numOfGame >= 10) {
            if (bonus == 0) throw new IllegalStateException("Cannot roll after game is over");
            else {
                score += bonus > 2 ? pins * 2 : pins;
                bonus -= bonus > 2 ? 2 : 1;
                gameScore += pins;
                if (gameScore > 10) throw new IllegalStateException("Pin count exceeds pins on the lane");
                if (gameScore == 10) gameScore = 0;
            }
        }
        else {
            score += pins * (bonus > 0 ? (bonus > 2 ? 3 : 2) : 1);
            bonus -= bonus == 0 ? 0 : (bonus > 2 ? 2 : 1);
            gameScore += pins;
            numOfThrows++;
            if (gameScore > 10) throw new IllegalStateException("Pin count exceeds pins on the lane");
            if (numOfThrows == 1 && gameScore == 10) {
                bonus += 2;
                numOfGame++;
                numOfThrows = 0;
                gameScore = 0;
            }
            if (numOfThrows == 2) {
                bonus += gameScore == 10 ? 1 : 0;
                numOfGame++;
                numOfThrows = 0;
                gameScore = 0;
            }
        }
    }

    public int score() {
        if (numOfGame == 10 && bonus == 0) return score;
        else throw new IllegalStateException("Score cannot be taken until the end of the game");
    }
}
