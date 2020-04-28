public class AirportSecurity implements Observer{

    private String parkingLotStatus;

    @Override
    public void update(Object status) {
        this.setParkingLotStatus((String) status);
    }
    public String getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(String parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }
}
