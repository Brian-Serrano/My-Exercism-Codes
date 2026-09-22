public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        double success = speed * 221;
        if(speed >= 5 && speed <= 8) success *= 0.9;
        if(speed == 9) success *= 0.8;
        if(speed == 10) success *= 0.77;
        return success;
    }

    public int workingItemsPerMinute(int speed) {
        double min = (productionRatePerHour(speed)/(speed * 60)) * speed;
        return (int) min;
    }
}