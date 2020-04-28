import java.util.LinkedHashMap;

public class Attendant {

    public void park(LinkedHashMap<String,Vehicle> parkingLot, Vehicle vehicle) {
        parkingLot.put(vehicle.getVehicleNumber(), vehicle);
    }

    public void unPark(LinkedHashMap<String,Vehicle> parkingLot, Vehicle vehicle) {
    }
}
