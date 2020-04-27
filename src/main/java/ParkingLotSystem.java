public class ParkingLotSystem {
    private Object vehicle;

    public boolean park(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    public boolean unPark(Object vehicle) {
    }
}
