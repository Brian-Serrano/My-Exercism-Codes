public class Say {
	private String[] onesDigit;
	private String[] tensDigit;
	private String[] teensDigit;
	
	public Say() {
		onesDigit = new String[] {
				"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
		};
		tensDigit = new String[] {
				"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
		};
		teensDigit = new String[] {
				"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
		};
	}
	
    public String say(long number) {
        String numberString = String.valueOf(number);
        String numberLetter = "";
        if(number == 0) {
        	numberLetter = "zero";
        }
        else if(number < 0 || number > 999999999999L) {
        	throw new IllegalArgumentException();
        }
        else {
        	for(int i = numberString.length() - 1; i >= 0; i--) {
            	int checkIndex = (numberString.length() - 1) - i;
        		int index = Character.getNumericValue(numberString.charAt(i));
            	switch(checkIndex % 3) {
            	case 0:
            		String thousands = " ";
                	switch(checkIndex) {
                	case 3:
                		thousands = CheckThousands(numberString, checkIndex, 7, " thousand ");
                		break;
                	case 6:
                		thousands = CheckThousands(numberString, checkIndex, 10, " million ");
                		break;
                	case 9:
                		thousands = CheckThousands(numberString, checkIndex, 13, " billion ");
                		break;
                	case 12:
                		thousands = CheckThousands(numberString, checkIndex, 16, " trillion ");
                		break;
                	}
                	if(i - 1 >= 0) {
                    	if(numberString.charAt(i - 1) != '1') {
                        	numberLetter = onesDigit[index] + thousands + numberLetter;
                    	}
                    	else {
                			numberLetter = teensDigit[index] + thousands + numberLetter;
                		}
                	}
                	else {
                		numberLetter = onesDigit[index] + thousands + numberLetter;
                	}
                	break;
            	case 1:
            		if(numberString.charAt(i) != '1' && numberString.charAt(i) != '0') {
            			if(numberString.charAt(i + 1) != '0') {
            				numberLetter = tensDigit[index] + "-" + numberLetter;
            			}
            			else {
            				numberLetter = tensDigit[index] + numberLetter;
            			}
            		}
                	break;
            	case 2:
            		if(numberString.charAt(i) != '0') {
                		if(numberString.charAt(i + 1) != '0' || numberString.charAt(i + 2) != '0') {
                			numberLetter = onesDigit[index] + " hundred " + numberLetter;
            			}
            			else {
            				numberLetter = onesDigit[index] + " hundred" + numberLetter;
            			}
            		}
                	break;
            	}
            }
        }
        
        return numberLetter.trim();
    }
    
    public String CheckThousands(String numberString, int index, int length, String name) {
    	if(numberString.length() >= length) {
    		if(!(numberString.charAt(index) == '0' && numberString.charAt(index + 1) == '0' && numberString.charAt(index + 2) == '0')) {
    			return name;
    		}
		}
		else {
			return name;
		}
    	return "";
    }
}