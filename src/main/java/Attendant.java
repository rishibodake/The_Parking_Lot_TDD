import java.util.HashMap;
import java.util.LinkedHashMap;

public class Attendant {

    public void park(HashMap<String, Vehicle> parkingLot, Vehicle vehicle, String getKey) {
        parkingLot.replace(getKey, vehicle);
    }


    public void unPark(LinkedHashMap<String,Vehicle> parkingLot, Vehicle vehicle) {
        parkingLot.remove(vehicle.getVehicleNumber());
    }
}
