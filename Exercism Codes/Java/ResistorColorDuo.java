class ResistorColorDuo {
    int value(String[] colors) {
        String[] arr = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};
        String[] colorRes = new String[2];
        for(int i=0; i<2; i++){
            for(int j=0; j<arr.length; j++){
                if(colors[i].equals(arr[j])){
                    colorRes[i] = Integer.toString(j);
                }
            }
        }
        return Integer.parseInt(String.join("", colorRes));
    }
}