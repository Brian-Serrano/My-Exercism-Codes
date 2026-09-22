import java.math.BigInteger;
import java.math.BigDecimal;

class Grains {
    private int chessboard = 64;
    BigInteger grainsOnSquare(final int square) {
        if(square <= 0 || square > 64){
            throw new IllegalArgumentException("square must be between 1 and 64");
        } else if(square == 1){
            return BigInteger.valueOf(1);
        } else {
            return new BigDecimal(Math.pow(2, square-1)).toBigInteger();
        }
    }

    BigInteger grainsOnBoard() {
        return new BigDecimal(Math.pow(2, chessboard)).toBigInteger().subtract(BigInteger.ONE);
    }

}