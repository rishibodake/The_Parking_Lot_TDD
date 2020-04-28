public class ParkingLotSystem {
    private Object vehicle;

    public void park(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle != null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot Is Full");
        this.vehicle = vehicle;
    }

    public void unPark(Object vehicle) throws ParkingLotSystemException{
        if(vehicle == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,"No Such Vehicle Found");
        if (this.vehicle.equals(vehicle))
            this.vehicle=null;
    }
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicle != vehicle)
            return true;
        return false;
    }
}
