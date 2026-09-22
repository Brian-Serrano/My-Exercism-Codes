import java.util.List;

public class Knapsack {

    public int maximumValue(int maxWeight, List<Item> items) {
        int[][] dp = new int[items.size() + 1][maxWeight + 1];

        for (int i = 1; i <= items.size(); i++) {
            Item item = items.get(i - 1);
            for (int j = 1; j <= maxWeight; j++) {
                if (item.weight <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.weight] + item.value);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[items.size()][maxWeight];
    }
}

class Item {
    public int weight, value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}