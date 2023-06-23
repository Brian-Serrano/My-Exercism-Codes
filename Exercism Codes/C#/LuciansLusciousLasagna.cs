class Lasagna
{
    public int ExpectedMinutesInOven(){
        return 40;
    }
    public int RemainingMinutesInOven(int rem){
        return ExpectedMinutesInOven() - rem;
    }
    public int PreparationTimeInMinutes(int layer){
        return layer*2;
    }
    public int ElapsedTimeInMinutes(int layer, int rem){
        return rem + PreparationTimeInMinutes(layer);
    }
}