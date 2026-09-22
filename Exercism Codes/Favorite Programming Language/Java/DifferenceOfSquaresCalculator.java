class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int sum = 0;
        for(int i = 1; i <= input; i++){
            sum += i;
        }
        return sum*sum;
    }

    int computeSumOfSquaresTo(int input) {
        int sum = 0;
        for(int i = 1; i <= input; i++){
            sum += i*i;
        }
        return sum;
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input)-computeSumOfSquaresTo(input);
    }

}