import java.util.*;

class WordSearcher {
    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        List<String> wordsLst = new ArrayList<>(words);
        Map<String, Optional<WordLocation>> res = new HashMap<>();
        for(int i = 0; i < wordsLst.size(); i++) {
            int len = wordsLst.get(i).length();
            String cw = wordsLst.get(i);
            int rl = grid.length;
            for(int j = 0; j < rl; j++) {
                int cl = grid[j].length;
                for(int k = 0; k < cl; k++) {
                    if(cw.charAt(0) == grid[j][k]) {
                        if(search(grid, cw, len <= j + 1, j, k, -1, 0)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k + 1, j - (len - 1) + 1))));
                        }
                        if(search(grid, cw, len <= k + 1, j, k, 0, -1)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k - (len - 1) + 1, j + 1))));
                        }
                        if(search(grid, cw, len <= rl - j, j, k, 1, 0)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k + 1, j + (len - 1) + 1))));
                        }
                        if(search(grid, cw, len <= cl - k, j, k, 0, 1)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k + (len - 1) + 1, j + 1))));
                        }
                        if(search(grid, cw, len <= j + 1 && len <= k + 1, j, k, -1, -1)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k - (len - 1) + 1, j - (len - 1) + 1))));
                        }
                        if(search(grid, cw, len <= j + 1 && len <= cl - k, j, k, -1, 1)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k + (len - 1) + 1, j - (len - 1) + 1))));
                        }
                        if(search(grid, cw, len <= rl - j && len <= k + 1, j, k, 1, -1)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k - (len - 1) + 1, j + (len - 1) + 1))));
                        }
                        if(search(grid, cw, len <= rl - j && len <= cl - k, j, k, 1, 1)) {
                            res.put(cw, Optional.of(new WordLocation(new Pair(k + 1, j + 1), new Pair(k + (len - 1) + 1, j + (len - 1) + 1))));
                        }
                    }
                }
            }
            if(!res.containsKey(cw)) {
                res.put(cw, Optional.empty());
            }
        }
        return res;
    }

    boolean search(final char[][] grid, String word, boolean condition, int x, int y, int xOffset, int yOffset) {
        if(condition) {
            int count = 1;
            for(int l = 1; l < word.length(); l++) {
                if(word.charAt(l) == grid[x + (l * xOffset)][y + (l * yOffset)]) {
                    count++;
                }
            }
            return count == word.length();
        }
        return false;
    }
}
