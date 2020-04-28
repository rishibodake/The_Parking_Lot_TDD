import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    Object vehicle = null;
    ParkingLotSystem parkingLotSystem = null;

    @Before
    public void setUp() {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }
    //Test For Park The Vehicle
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertEquals(true, isParked);
    }
    //Test For Check If Already vehicle is parked
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException{
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertEquals(false, isParked);
        }
        catch (ParkingLotSystemException e){
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, e.excetionType);
        }
    }
    //Test For UnPark The Vehicle
    @Test
    public void givenAVehicle_WhenUnParked_ReturnTrue() throws ParkingLotSystemException {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertEquals(true, isUnParked);
    }

}
