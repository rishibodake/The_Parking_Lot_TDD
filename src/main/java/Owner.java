import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Owner implements Observer,Availability {
    private String parkingLotStatus;
    private String parkingCharge;

    //overided method update from Observer Interface
    @Override
    public void update(Object status) {
        this.setParkingLotStatus((String) status);
    }
    //getter
    public void setParkingLotStatus(String parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }

    public String getParkingLotStatus() {
        return parkingLotStatus;
    }

    @Override
    public void isPresent(LinkedHashMap<String, Vehicle> parkingLot, Vehicle vehicle) throws ParkingLotSystemException {
        if (vehicle == null) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE, "No such a vehicle");
        }
    }
    @Override
    public void isAvailable(Map<String, Vehicle> parkingLot, int parkingLotCapacity) throws ParkingLotSystemException {
        if (!parkingLot.containsValue(null)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking lot is full");
        }
    }

    public void setParkingCharge(String parkingCharge) {
        this.parkingCharge = parkingCharge;
    }
}
