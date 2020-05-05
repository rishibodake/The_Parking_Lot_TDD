public class Vehicle {
    private Driver driver;
    private  String vehicleNumber;
    private  String name;
    enum DriverType {
        HANDICAP,NORMAL
    }
//Constructor For With parameter
    public Vehicle(String vehicleNumber, String name,Driver driver) {
        this.vehicleNumber = vehicleNumber;
        this.name = name;
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }
}
