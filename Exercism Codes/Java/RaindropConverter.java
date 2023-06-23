class RaindropConverter {

    String convert(int number) {
        String raindrops = "";
        boolean three = number % 3 == 0;
        boolean five = number % 5 == 0;
        boolean seven = number % 7 == 0;
        if(three){
            raindrops += "Pling";
        }
        if(five){
            raindrops += "Plang";
        }
        if(seven){
            raindrops += "Plong";
        }
        if(!three && !five && !seven){
            raindrops = "" + number;
        }
        return raindrops;
    }

}