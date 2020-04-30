public class Vehicle {
    private  String vehicleNumber;
    private  String name;
//Constructor For With parameter
    public Vehicle(String vehicleNumber, String name) {
        this.vehicleNumber = vehicleNumber;
        this.name = name;
    }
//Getter
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public String getName()
    {
        return name;
    }
}
