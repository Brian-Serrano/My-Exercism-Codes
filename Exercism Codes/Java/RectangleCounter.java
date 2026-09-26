class RectangleCounter {

    int countRectangles(String[] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length(); j++) {
                if(grid[i].charAt(j) == '+') {
                    for(int k = j + 1; k < grid[i].length(); k++) {
                        if(grid[i].substring(j, k + 1).matches("^\\+[+-]*\\+$")) {
                            for(int l = i + 1; l < grid.length; l++) {
                                if(grid[l].substring(j, k + 1).matches("^[+|].*[+|]$")) {
                                    if(grid[l].substring(j, k + 1).matches("^\\+[+-]*\\+$")) count++;
                                }
                                else break;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}