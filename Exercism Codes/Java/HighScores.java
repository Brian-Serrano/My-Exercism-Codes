import java.util.Collections;

class HighScores {

    private List<Integer> highScores;
    
    public HighScores(List<Integer> highScores) {
        this.highScores = highScores;
    }

    List<Integer> scores() {
        return highScores;
    }

    Integer latest() {
        return highScores.get(highScores.size() - 1);
    }

    Integer personalBest() {
        int max = 0;
        for(int i = 0; i < highScores.size(); i++){
            if(highScores.get(i) > max){
                max = highScores.get(i);
            }
        }
        return max;
    }

    List<Integer> personalTopThree() {
        List<Integer> temp = new ArrayList<Integer>(highScores);
        Collections.sort(temp, Collections.reverseOrder());
        return temp.subList(0, Math.min(3, temp.size()));
    }

}