public class Hamming {
    private String leftStrand;
    private String rightStrand;
    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        this.leftStrand = leftStrand;
        this.rightStrand = rightStrand;
    }

    public int getHammingDistance() throws IllegalArgumentException {
        char[] left = leftStrand.toCharArray();
        char[] right = rightStrand.toCharArray();
        int distance = 0;
        for (int i = 0; i < left.length; i++) {
            if (left[i] != right[i]){
                distance++;
            }
        }
        return distance;
    }
}
