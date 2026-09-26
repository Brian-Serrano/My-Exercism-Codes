public class Lasagna {
    public final int min = 40;
    public final int lay = 2;
    
    public int expectedMinutesInOven(){
        return min;
    }

    public int remainingMinutesInOven(int rem){
        return min - rem;
    }

    public int preparationTimeInMinutes(int layer){
        return layer*lay;
    }

    public int totalTimeInMinutes(int layer, int rem){
        return rem+(layer*lay);
    }
}