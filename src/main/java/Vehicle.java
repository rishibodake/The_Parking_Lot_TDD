public class Vehicle {
    public  DriverType driverType;
    private  String vehicleNumber;
    private  String name;
    enum DriverType {
        HANDICAP,NORMAL
    }
//Constructor For With parameter
    public Vehicle(String vehicleNumber, String name,DriverType driverType) {
        this.vehicleNumber = vehicleNumber;
        this.name = name;
        this.driverType = driverType;
    }

    public DriverType getDriverType() {
        return driverType;
    }
}
