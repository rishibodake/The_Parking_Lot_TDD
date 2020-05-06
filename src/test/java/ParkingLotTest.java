import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    Owner owner = null;
    AirportSecurity airportSecurity = null;
    Attendant attendant = null;
    LinkedHashMap<String, Vehicle> parkingLot = null;
    LinkedHashMap<String, Integer> availableLot = null;

    @Before
    public void setUp() {
        owner = new Owner();
        airportSecurity = new AirportSecurity();
        attendant = new Attendant();
        parkingLot = new LinkedHashMap();
        availableLot = new LinkedHashMap<String, Integer>();
    }
    //Test For Park The Vehicle
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH11N2318","Swift",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertEquals(true, isParked);
    }
    //Test For Check If Already vehicle is parked
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException{
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        Vehicle vehicle1 = new Vehicle("MH17BW9898", "Jeep",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle1);
        Assert.assertEquals(false, isParked);
    }
    //Test For UnPark The Vehicle
    @Test
    public void givenAVehicle_WhenUnParked_ReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(true, isUnParked);
    }

    //Test To Handle Null Type Exception
    @Test
    public void givenANullVehicle_WhenUnParked_ShouldThrowException(){
        try {
            parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
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
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(false, isUnParked);
    }

    //Test Case In Parking Lot Is Full
    @Test
    public void givenAVehicles_WhenParkingLotIsFull_ShouldThrowException() {
        try {
            parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            int numberOfCars = 8;
            for (int vehicleIteration = 0; vehicleIteration < numberOfCars; vehicleIteration++) {
                Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "Audi",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
                parkingLotSystem.park(vehicle);
            }
            vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertEquals(false, isUnParked);
            Vehicle vehicle1 = new Vehicle("MH50A0001", "Fortuner",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("MH10BQ8108", "BULLET",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle2);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertEquals(true, isParked);
        }
        catch (ParkingLotSystemException e)
        {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,e.exceptionType);

        }
    }
    //Test For Inform owner When Parking Lot Is Full
    @Test
    public void givenAVehicles_WhenParkingLotIsFull_ShouldInformToOwner(){
        try {
            parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            parkingLotSystem.addObserver(owner);
            int numberOfCars = 8;
            for (int vehicleIteration = 0; vehicleIteration < numberOfCars; vehicleIteration++) {
                Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "Audi",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
                parkingLotSystem.park(vehicle);
            }
            vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
            Vehicle vehicle1 = new Vehicle("MH10BQ8108", "BULLET",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("MH10BQ8109", "Marshal",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
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
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        parkingLotSystem.addObserver(airportSecurity);
        int numberOfCars = 8;
        for (int vehicleIteration = 0; vehicleIteration < numberOfCars; vehicleIteration++) {
            Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "Audi",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
        }
        vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Marshal",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle1);
        Assert.assertEquals("Full", airportSecurity.getParkingLotStatus());
        Assert.assertEquals("Full", owner.getParkingLotStatus());
    }

    //Test Case When The Parking Lot Has Space Again
    @Test
    public void givenAVehicles_WhenParkingLotHasSpaceAgain_ShouldInformToParkingLotOwner() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Marshal",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle1);
        Assert.assertEquals("Have Space", owner.getParkingLotStatus());
    }

    //test for attendant park car whenever owner want
    @Test
    public void givenVehicle_WhenOwnerWantAttendant_ShouldParkTheCar() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        vehicle = new Vehicle("MH13AN0808", "McLaren",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Ferari",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle1);
    }

    //Test For Find The Vehicle Number
    @Test
    public void givenVehicle_WhenWantToFindCar_ShouldReturnGetNumberInParkingLot() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        int TotalNumberOfCars = 8;
        for (int vehicleIteration = 0; vehicleIteration < TotalNumberOfCars; vehicleIteration++) {
            Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "BMW",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
        }
        Vehicle vehicle2 = new Vehicle("55", "Thar",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle2);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle2);
        Assert.assertEquals("P303", numberInParkingLot);
    }

    //Test For Charging The Vehicle When Its Park
    @Test
    public void givenVehicleParkInLot_WhenCharge_ThenReturnTrue() {
        try {
            parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            parkingLotSystem.addObserver(owner);
            Vehicle vehicle = new Vehicle("100", "Benz",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertEquals(true,isParked);
        } catch (ParkingLotSystemException e)
        {
            e.printStackTrace();
        }
    }
    //Test For Distribute The Parking Lot Evenly
    @Test
    public void givenVehicle_WhenOwnerWantAttendant_ShouldEvenlyDistributeCar() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        int numberOfCars = 8;
        for (int i = 0; i < numberOfCars; i++) {
            Vehicle vehicle = new Vehicle(Integer.toString(i), "BMW",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
        }
        Vehicle vehicle2 = new Vehicle("55", "Thar",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("75", "Thar",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.unPark(vehicle3);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle2);
        Assert.assertEquals("P303", numberInParkingLot);
    }

    @Test
    public void givenVehicle_WhenHandicapDriverWantAttendant_ShouldGiveNearestFreeSpace() throws ParkingLotSystemException {
        int numberOfCars = 8;
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        Vehicle vehicle2 = new Vehicle("55", "Thar", new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("75", "Thar", new Driver(Driver.DriverType.HANDICAP),Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle3);
        for (int index = 0; index < numberOfCars; index++) {
            Vehicle vehicle = new Vehicle(Integer.toString(index), "BMW", new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
        }
        parkingLotSystem.unPark(vehicle3);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle3);
        Assert.assertEquals("P101", numberInParkingLot);
    }

    //Test To Direct Large Vehicles To The Lot Which Has Max Space
    @Test
    public void givenVehicle_WhenOwnerWantAttendant_ShouldDirectLargeVehicleToHighestNumberOfFreeSpace() throws ParkingLotSystemException {
        int numberOfCars = 8;
        parkingLotSystem = new ParkingLotSystem(owner, attendant, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        Vehicle vehicle2 = new Vehicle("5", "Alto", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("7", "tata prima", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.LARGE);
        parkingLotSystem.park(vehicle3);
        for (int index = 0; index < numberOfCars; index++) {
            Vehicle vehicle = new Vehicle(Integer.toString(index), "BMW", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL);
            parkingLotSystem.park(vehicle);
        }
        parkingLotSystem.unPark(vehicle3);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle2);
        Assert.assertEquals("P101", numberInParkingLot);
    }

}

