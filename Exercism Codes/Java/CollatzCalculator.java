class CollatzCalculator {

    int computeStepCount(int start) {
        if(start <= 0) throw new IllegalArgumentException("Only natural numbers are allowed");
        int count = 0;
        while(start != 1) {
            start = start % 2 == 0 ? start / 2 : (start * 3) + 1;
            count++;
        }
        return count;
    }

}
