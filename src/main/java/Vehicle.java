public class Vehicle {
    private Driver driver;
    private  String vehicleNumber;
    private  String name;
    private VehicleType vehicleType;
    enum VehicleType {
        SMALL, LARGE
    }
//Constructor For With parameter
    public Vehicle(String vehicleNumber, String name,Driver driver,VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.name = name;
        this.driver = driver;
        this.vehicleType = vehicleType;
    }

    public VehicleType getDriverType() {
        return vehicleType;
    }
}
