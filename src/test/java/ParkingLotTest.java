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
        boolean isParked = parkingLotSystem.park(vehicle);
        Assert.assertEquals(true, isParked);
    }

    //Test For Check If Already vehicle is parked
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertEquals(false, isParked);
    }
}
