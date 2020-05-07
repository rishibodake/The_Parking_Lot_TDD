public class Vehicle {
    private Color color;
    private Driver driver;
    private  String vehicleNumber;
    private  String name;
    private VehicleType vehicleType;

    //ENUM for the type of vehicle
    enum VehicleType {
        SMALL, LARGE
    }

    //ENUM for the color of vehicle
    enum Color {
        RED,BLUE,BLACK,GREEN,WHITE
    }
//Constructor For With parameter
    public Vehicle(String vehicleNumber, String name,Driver driver,VehicleType vehicleType,Color color) {
        this.vehicleNumber = vehicleNumber;
        this.name = name;
        this.driver = driver;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    public Driver getDriver() {
        return driver;
    }
    public VehicleType getDriverType() {
        return vehicleType;
    }
}
