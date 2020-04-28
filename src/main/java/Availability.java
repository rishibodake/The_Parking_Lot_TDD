import java.util.LinkedHashMap;
public interface Availability {
    boolean isAvailable(LinkedHashMap<String, Vehicle> parkingLot, int parkingLotSize) throws ParkingLotSystemException;
    boolean isPresent(LinkedHashMap<String, Vehicle> parkingLot, Vehicle vehicle) throws ParkingLotSystemException;
}
