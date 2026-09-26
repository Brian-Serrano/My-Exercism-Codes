class BowlingGame {

    var score = 0
    var numOfThrows = 0
    var numOfGame = 0
    var gameScore = 0
    var bonus = 0

    fun roll(pins: Int) {
        if (pins < 0) {
            throw IllegalStateException()
        }
        if (numOfGame >= 10) {
            if (bonus == 0) {
                throw IllegalStateException()
            }
            else {
                score += if (bonus > 2) pins * 2 else pins
                bonus -= if (bonus > 2) 2 else 1
                gameScore += pins
                if (gameScore > 10) {
                    throw IllegalStateException()
                }
                if (gameScore == 10) {
                    gameScore = 0
                }
            }
        }
        else {
            score += pins * (if (bonus > 0) (if (bonus > 2) 3 else 2) else 1)
            bonus -= if (bonus == 0) 0 else (if (bonus > 2) 2 else 1)
            gameScore += pins
            numOfThrows++
            if (gameScore > 10) {
                throw IllegalStateException()
            }
            if (numOfThrows == 1 && gameScore == 10) {
                bonus += 2
                numOfGame++
                numOfThrows = 0
                gameScore = 0
            }
            if (numOfThrows == 2) {
                bonus += if (gameScore == 10) 1 else 0
                numOfGame++
                numOfThrows = 0
                gameScore = 0
            }
        }
    }

    fun score(): Int {
        if (numOfGame == 10 && bonus == 0) {
            return score
        }
        else {
            throw IllegalStateException()
        }
    }
}
