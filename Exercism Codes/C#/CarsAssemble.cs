using System;

static class AssemblyLine
{
    public static double SuccessRate(int speed)
    {
        double successRate;
        if(speed == 0) {
            successRate = 0;
        } else if(speed > 0 && speed <= 4){
            successRate = 1;
        } else if(speed > 4 && speed <= 8){
            successRate = 0.9;
        } else if(speed == 9){
            successRate = 0.8;
        } else if(speed == 10){
            successRate = 0.77;
        } else {
            successRate = 0;
        }
        return successRate;
    }
    
    public static double ProductionRatePerHour(int speed)
    {
        return (221 * speed) * SuccessRate(speed);
    }

    public static int WorkingItemsPerMinute(int speed)
    {
        return (int)(ProductionRatePerHour(speed) / 60);
    }
}