import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;

    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem();
    }
    //Test For Park The Vehicle
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        vehicle = new Vehicle("MH11N2318","Swift");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertEquals(true, isParked);
    }
    //Test For Check If Already vehicle is parked
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException{
        vehicle = new Vehicle("MH15AN0101", "Alto");
        Vehicle vehicle1 = new Vehicle("MH17BW9898", "Jeep");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle1);
        Assert.assertEquals(false, isParked);
    }
    //Test For UnPark The Vehicle
    @Test
    public void givenAVehicle_WhenUnParked_ReturnTrue() throws ParkingLotSystemException {
        vehicle = new Vehicle("MH15AN0101", "Alto");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(true, isUnParked);
    }

    //Test To Handle Null Type Exception
    @Test
    public void givenANullVehicle_WhenUnParked_ShouldThrowException() throws ParkingLotSystemException {
        try {
            vehicle = new Vehicle("MH15AN0101", "Alto");
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(null);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertEquals(true, isUnParked);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,e.exceptionType);
        }
    }
    //Check parked vehicle for unParking
    @Test
    public void givenAVehicle_WhenAlreadyParkedAndCheckIfUnPark_ShouldReturnFalse() throws ParkingLotSystemException {
        vehicle = new Vehicle("MH15AN0101", "Alto");
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(false, isUnParked);
    }

    //Test Case In Parking Lot Is Full
    @Test
    public void givenAVehicles_WhenParkingLotIsFull_ShouldThrowException() throws ParkingLotSystemException {
        try {
            vehicle = new Vehicle("1", "car");
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertEquals(false, isUnParked);
            Vehicle vehicle1 = new Vehicle("2", "car1");
            parkingLotSystem.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("3", "car2");
            parkingLotSystem.park(vehicle2);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertEquals(true, isParked);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, e.exceptionType);
        }
    }
}
