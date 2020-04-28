import java.util.HashMap;

public class ParkingLotSystem {
    Owner owner = new Owner();
    HashMap<String, Object> parkingLot = new HashMap();
    boolean isFull, isEmpty;
    int parkingLotSize = 2;

    public void park(Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLot.size() < parkingLotSize) {
            parkingLot.put(vehicle.getVehicleNumber(), vehicle);
        } else if (parkingLot.size() == parkingLotSize) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,"Parking Lot Is Full");
        }
        if (parkingLot.size() == parkingLotSize)
            setInformation("Full");
    }
    }

    public void unPark(Vehicle vehicle) throws ParkingLotSystemException{
        if(vehicle == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,"No Such Vehicle Found");
        else if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            parkingLot.remove(vehicle.getVehicleNumber());
        }

    public boolean isVehicleParked(Vehicle vehicle) {
            if (parkingLot.containsKey(vehicle.getVehicleNumber()))
            return true;
        return false;
    }

    public boolean isVehicleUnParked(Vehicle vehicle) {
        if (!parkingLot.containsKey(vehicle.getVehicleNumber()))
            return true;
        return false;
    }
}
