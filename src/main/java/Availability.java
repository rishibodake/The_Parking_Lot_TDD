import java.util.LinkedHashMap;
import java.util.Map;

public interface Availability {
    void isAvailable(Map<String, Vehicle> parkingLot, int parkingLotCapacity) throws ParkingLotSystemException;
    void isPresent(LinkedHashMap<String, Vehicle> parkingLot, Vehicle vehicle) throws ParkingLotSystemException;
}
