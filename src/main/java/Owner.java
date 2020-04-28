import java.util.LinkedHashMap;

public class Owner implements Observer {
    private String parkingLotStatus;

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

    public boolean isPresent(LinkedHashMap<String,Vehicle> parkingLot, Vehicle vehicle) throws ParkingLotSystemException {
        if (vehicle == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE, "No such a vehicle");
        else if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            return true;
        return false;
    }
}
