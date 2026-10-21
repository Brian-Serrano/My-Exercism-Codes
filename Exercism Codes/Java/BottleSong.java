class BottleSong {

    private final String[][] numbers = new String[][] {
        new String[] { "One", "no" },
        new String[] { "Two", "one" },
        new String[] { "Three", "two" },
        new String[] { "Four", "three" },
        new String[] { "Five", "four" },
        new String[] { "Six", "five" },
        new String[] { "Seven", "six" },
        new String[] { "Eight", "seven" },
        new String[] { "Nine", "eight" },
        new String[] { "Ten", "nine" },
    };

    String recite(int startBottles, int takeDown) {
        StringBuilder result = new StringBuilder();

        for (int i = startBottles; i > startBottles - takeDown; i--) {
            String n = numbers[i - 1][0];
            String bottle1 = n.equals("One") ? "bottle" : "bottles";
            result.append(n + " green " + bottle1 + " hanging on the wall,\n");
            result.append(n + " green " + bottle1 + " hanging on the wall,\n");

            result.append("And if one green bottle should accidentally fall,\n");

            String s = numbers[i - 1][1];
            String bottle2 = s.equals("one") ? "bottle" : "bottles";
            result.append("There'll be " + s + " green " + bottle2 + " hanging on the wall.\n");

            if (i != startBottles - takeDown + 1)
                result.append("\n");
        }

        return result.toString();
    }
}