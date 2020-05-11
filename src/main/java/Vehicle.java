public class Vehicle {
    private Color color;
    private Driver driver;
    private  String vehicleNumber;
    private  String name;
    private VehicleType vehicleType;
    private String vehicleId;
    private Vehicle_Brand vehicle_brand;


    public VehicleType getVehicleType() {
        return vehicleType;
    }


    //ENUM For Vehicle Brand
    public enum Vehicle_Brand {
        TOYOTA,HONDA;
    }
    //ENUM for the type of vehicle
    enum VehicleType {
        SMALL, LARGE
    }

    //ENUM for the color of vehicle
    enum Color {
        RED,BLUE,BLACK,GREEN,WHITE
    }
//Constructor For With parameter
    public Vehicle(String vehicleNumber, Vehicle_Brand vehicle_brand,Driver driver,VehicleType vehicleType,Color color) {
        this.vehicleNumber = vehicleNumber;
        this.driver = driver;
        this.vehicleType = vehicleType;
        this.color = color;
        this.vehicle_brand = vehicle_brand;

    }

    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }

    public Driver getDriver() {
        return driver;
    }
    public VehicleType getDriverType() {
        return vehicleType;
    }
    public Vehicle_Brand getVehicle_brand() {
        return vehicle_brand;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
