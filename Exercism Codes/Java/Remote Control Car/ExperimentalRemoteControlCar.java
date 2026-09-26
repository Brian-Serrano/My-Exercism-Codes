public class ExperimentalRemoteControlCar implements RemoteControlCar{
    private int speed = 20;
    private int distanceTravelled;
    @Override
    public void drive() {
        distanceTravelled += speed;
    }
    @Override
    public int getDistanceTravelled() {
        return distanceTravelled;
    }
}