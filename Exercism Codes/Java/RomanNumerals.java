import java.util.List;

class RomanNumerals {
    private final int normalNumber;

    public RomanNumerals(int number) {
        normalNumber = number;
    }

    public String getRomanNumeral() {
        return getRomanRecursively(normalNumber, new StringBuilder());
    }

    private String getRomanRecursively(int number, StringBuilder romanNumeral) {
        List<Symbols> symbols = List.of(
                new Symbols(1000, "M"),
                new Symbols(900, "CM"),
                new Symbols(500, "D"),
                new Symbols(400, "CD"),
                new Symbols(100, "C"),
                new Symbols(90, "XC"),
                new Symbols(50, "L"),
                new Symbols(40, "XL"),
                new Symbols(10, "X"),
                new Symbols(9, "IX"),
                new Symbols(5, "V"),
                new Symbols(4, "IV"),
                new Symbols(1, "I")
        );

        for (Symbols symbol : symbols) {
            if(number >= symbol.number) {
                romanNumeral.append(symbol.symbol);
                number -= symbol.number;
                return getRomanRecursively(number, romanNumeral);
            }
        }

        return romanNumeral.toString();
    }

    private static class Symbols {
        public int number;
        public String symbol;
        public Symbols(int n, String s) {
            number = n;
            symbol = s;
        }
    }
}
