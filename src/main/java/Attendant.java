import java.util.*;

public class Attendant {

    private String vehicleKey;
    private String parkMethod = "PARK";

    public void park(HashMap<String, Vehicle> parkingLot, Vehicle vehicle, LinkedHashMap<String, Integer> availableLot) {
        String keyAvailable = Collections.max(availableLot.entrySet(), Map.Entry.comparingByValue()).getKey();
        Iterator<String> parkingLotItr = parkingLot.keySet().iterator();
        while (parkingLotItr.hasNext()) {
            vehicleKey = parkingLotItr.next();
            if (vehicle.getDriverType().equals(Vehicle.DriverType.HANDICAP) && parkingLot.get(vehicleKey) == null) {
                parkingLot.replace(vehicleKey, vehicle);
                updateAvailableLot(availableLot, vehicleKey, parkMethod);
                break;
            } else if (vehicleKey.substring(0, 2).equals(keyAvailable) && parkingLot.get(vehicleKey) == null) {
                parkingLot.replace(vehicleKey, vehicle);
                updateAvailableLot(availableLot, vehicleKey, parkMethod);
                break;
            }
        }
            }


    public void unPark(LinkedHashMap<String, Vehicle> parkingLot, Vehicle vehicle, LinkedHashMap<String, Integer> availableLot) {
        Iterator<String> parkingLotItr = parkingLot.keySet().iterator();
        while (parkingLotItr.hasNext()) {
            String key = parkingLotItr.next();
            if (parkingLot.get(key) == vehicle)
                vehicleKey = key;
        }
        parkingLot.replace(vehicleKey, null);
        Iterator<String> availableLotItr = availableLot.keySet().iterator();
        while (availableLotItr.hasNext()) {
            String key = availableLotItr.next();
            int value = (availableLot.get(key) + 1);
            if (vehicleKey.substring(0, 2).equals(key)) {
                availableLot.replace(key, value);
            }
        }
    }
}
