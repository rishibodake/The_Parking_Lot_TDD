import java.util.LinkedHashMap;

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
    public boolean isPresent(LinkedHashMap<String,Vehicle> parkingLot, Vehicle vehicle) throws ParkingLotSystemException {
        if (vehicle == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE, "No such a vehicle");
        else if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            return true;
        return false;
    }
    @Override
    public boolean isAvailable(LinkedHashMap<String,Vehicle> parkingLot, int parkingLotSize) throws ParkingLotSystemException {
        if (parkingLot.size() < parkingLotSize) {
            return true;
        } else if (parkingLot.size() == parkingLotSize) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking lot is full");
        }
        return false;
    }

    public void setParkingCharge(String parkingCharge) {
        this.parkingCharge = parkingCharge;
    }
}
