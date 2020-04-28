import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    Owner owner = null;
    AirportSecurity airportSecurity = null;
    Attendant attendant = null;

    @Before
    public void setUp() throws{
        parkingLotSystem = new ParkingLotSystem();
        owner = new Owner();
        airportSecurity = new AirportSecurity();
        attendant = new Attendant();
    }
    //Test For Park The Vehicle
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant);
        vehicle = new Vehicle("MH11N2318","Swift");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertEquals(true, isParked);
    }
    //Test For Check If Already vehicle is parked
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException{
        parkingLotSystem = new ParkingLotSystem(owner, attendant);
        vehicle = new Vehicle("MH15AN0101", "Alto");
        Vehicle vehicle1 = new Vehicle("MH17BW9898", "Jeep");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle1);
        Assert.assertEquals(false, isParked);
    }
    //Test For UnPark The Vehicle
    @Test
    public void givenAVehicle_WhenUnParked_ReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant);
        vehicle = new Vehicle("MH15AN0101", "Alto");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(true, isUnParked);
    }

    //Test To Handle Null Type Exception
    @Test
    public void givenANullVehicle_WhenUnParked_ShouldThrowException(){
        try {
            parkingLotSystem = new ParkingLotSystem(owner, attendant);
            vehicle = new Vehicle("MH15AN0101", "Alto");
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(null);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertEquals(true, isUnParked);
        } catch (ParkingLotSystemException e)
        {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,e.exceptionType);
        }
    }
    //Check parked vehicle for unParking
    @Test
    public void givenAVehicle_WhenAlreadyParkedAndCheckIfUnPark_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant);
        vehicle = new Vehicle("MH15AN0101", "Alto");
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(false, isUnParked);
    }

    //Test Case In Parking Lot Is Full
    @Test
    public void givenAVehicles_WhenParkingLotIsFull_ShouldThrowException() {
        try {
            parkingLotSystem = new ParkingLotSystem(owner, attendant);
            vehicle = new Vehicle("MH13AN0808", "Bolero");
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertEquals(false, isUnParked);
            Vehicle vehicle1 = new Vehicle("MH50A0001", "Fortuner");
            parkingLotSystem.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("MH10BQ8108", "BULLET");
            parkingLotSystem.park(vehicle2);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertEquals(true, isParked);
        }
        catch (ParkingLotSystemException e)
        {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,e.exceptionType);

        }
    }

    @Test
    public void givenAVehicles_WhenParkingLotIsFull_ShouldInformToOwner(){
        try {
            parkingLotSystem = new ParkingLotSystem(owner, attendant);
            parkingLotSystem.addObserver(owner);
            vehicle = new Vehicle("MH13AN0808", "Bolero");
            parkingLotSystem.park(vehicle);
            Vehicle vehicle1 = new Vehicle("MH10BQ8108", "BULLET");
            parkingLotSystem.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("MH10BQ8109", "Marshal");
            parkingLotSystem.park(vehicle2);
            Assert.assertEquals("Full", owner.getParkingLotStatus());
        }
        catch (ParkingLotSystemException e)
        {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,e.exceptionType);
        }
    }
    //Test To Inform Airport Security
    @Test
    public void givenAVehicles_WhenParkingLotIsFull_ShouldInformToAirportSecurityAndOwner() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant);
        parkingLotSystem.addObserver(owner);
        parkingLotSystem.addObserver(airportSecurity);
        vehicle = new Vehicle("MH13AN0808", "Bolero");
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Marshal");
        parkingLotSystem.park(vehicle1);
        Assert.assertEquals("Full", airportSecurity.getParkingLotStatus());
        Assert.assertEquals("Full", owner.getParkingLotStatus());
    }

    //Test Case When The Parking Lot Has Space Again
    @Test
    public void givenAVehicles_WhenParkingLotHasSpaceAgain_ShouldInformToParkingLotOwner() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant);
        parkingLotSystem.addObserver(owner);
        vehicle = new Vehicle("MH13AN0808", "Bolero");
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Marshal");
        parkingLotSystem.park(vehicle1);
        Assert.assertEquals("Full", owner.getParkingLotStatus());
        parkingLotSystem.unPark(vehicle1);
        Assert.assertEquals("Have Space", owner.getParkingLotStatus());
    }

    //test for attendant park car whenever owner want
    @Test
    public void givenVehicle_WhenOwnerWantAttendant_ShouldParkTheCar() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant);
        parkingLotSystem.addObserver(owner);
        vehicle = new Vehicle("MH13AN0808", "McLaren");
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Ferari");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle1);
        System.out.println(parkingLotSystem.isVehicleParked(vehicle));
    }
}

