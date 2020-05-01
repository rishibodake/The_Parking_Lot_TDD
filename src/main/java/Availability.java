import java.util.LinkedHashMap;
import java.util.Map;

public interface Availability {
    String isAvailable(Map<String, Vehicle> parkingLot, int parkingLotCapacity) throws ParkingLotSystemException;
    String isPresent(LinkedHashMap<String, Vehicle> parkingLot, Vehicle vehicle) throws ParkingLotSystemException;
}
