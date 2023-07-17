public class ArmstrongNumbers {

    public boolean isArmstrongNumber(int numberToCheck) {
    	String numberString = String.valueOf(numberToCheck);
    	int sum = 0;
    	for(int i = 0; i < numberString.length(); i++) {
    		sum += Math.pow(Character.getNumericValue(numberString.charAt(i)), numberString.length());
    	}
    	
    	return sum == numberToCheck;
    }

}