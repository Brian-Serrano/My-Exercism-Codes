public class ElonsToyCar {
    private int meter = 0;
    private int battery = 100;
    
    public static ElonsToyCar buy() {
        return new ElonsToyCar();
    }

    public String distanceDisplay() {
        return "Driven " + meter + " meters";
    }

    public String batteryDisplay() {
        return (battery == 0) ? "Battery empty" : "Battery at " + battery + "%";
    }

    public void drive() {
        if(battery != 0){ 
            meter += 20;
            battery--;
        }
    }
}