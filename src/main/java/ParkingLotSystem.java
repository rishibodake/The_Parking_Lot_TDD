public class ParkingLotSystem {
    private Object vehicle;

    public void park(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle != null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot Is Full");
        this.vehicle = vehicle;
    }

    public void unPark(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            this.vehicle=null;
    }
}
