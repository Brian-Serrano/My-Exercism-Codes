class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
    private int speed = 10;
    private int distanceTravelled;
    private int numberOfVictories;
    @Override
    public void drive() {
        distanceTravelled += speed;
    }
    @Override
    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    @Override
    public int compareTo(ProductionRemoteControlCar that) {
        return this.numberOfVictories - that.getNumberOfVictories();
    }
}