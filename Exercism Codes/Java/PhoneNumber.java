class PhoneNumber {
    private String number;

    public PhoneNumber(String number) {
        number = number.replaceAll("[\\s\\-()\\.+]", "");
        if(number.length() == 11) {
            if(number.charAt(0) != '1') throw new IllegalArgumentException("11 digits must start with 1");
            else number = number.substring(1);
        }
        else if(number.length() > 11) throw new IllegalArgumentException("more than 11 digits");
        else if(number.length() < 10) throw new IllegalArgumentException("incorrect number of digits");

        if(number.matches(".*[\\p{P}].*")) throw new IllegalArgumentException("punctuations not permitted");
        if(number.matches(".*[\\p{L}].*")) throw new IllegalArgumentException("letters not permitted");
        if(number.charAt(0) == '0') throw new IllegalArgumentException("area code cannot start with zero");
        if(number.charAt(0) == '1') throw new IllegalArgumentException("area code cannot start with one");
        if(number.charAt(3) == '0') throw new IllegalArgumentException("exchange code cannot start with zero");
        if(number.charAt(3) == '1') throw new IllegalArgumentException("exchange code cannot start with one");
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}