import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    Owner owner = null;
    AirportSecurity airportSecurity = null;
    LinkedHashMap<String, Slot> parkingLot = null;
    LinkedHashMap<String, Integer> availableLot = null;
    ParkingLot parkingLotHandler = null;
    Map<String, Slot> listForPoliceDepartment = null;
    List<Attendant> attendants = null;

    @Before
    public void setUp() {
        attendants= new ArrayList<Attendant>();
        owner = new Owner(attendants);
        airportSecurity = new AirportSecurity();
        parkingLot = new LinkedHashMap();
        availableLot = new LinkedHashMap<String, Integer>();
        parkingLotHandler = new ParkingLot();
        listForPoliceDepartment = new HashMap<>();
    }
    //Test For Park The Vehicle
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH11N2318","Swift",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertEquals(true, isParked);
    }
    //Test For Check If Already vehicle is parked
    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException{
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        Vehicle vehicle1 = new Vehicle("MH17BW9898", "Jeep",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle1);
        Assert.assertEquals(false, isParked);
    }
    //Test For UnPark The Vehicle
    @Test
    public void givenAVehicle_WhenUnParked_ReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(true, isUnParked);
    }

    //Test To Handle Null Type Exception
    @Test
    public void givenANullVehicle_WhenUnParked_ShouldThrowException(){
        try {
            parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
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
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        vehicle = new Vehicle("MH15AN0101", "Alto",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertEquals(false, isUnParked);
    }

    //Test Case In Parking Lot Is Full
    @Test
    public void givenAVehicles_WhenParkingLotIsFull_ShouldThrowException() {
        try {
            parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            int numberOfCars = 9;
            for (int vehicleIteration = 0; vehicleIteration < numberOfCars; vehicleIteration++) {
                Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "Audi",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
                parkingLotSystem.park(vehicle);
            }
            vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertEquals(false, isUnParked);
            Vehicle vehicle1 = new Vehicle("MH50A0001", "Fortuner",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("MH10BQ8108", "BULLET",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
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
            parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            parkingLotSystem.addObserver(owner);
            int numberOfCars = 8;
            for (int vehicleIteration = 0; vehicleIteration < numberOfCars; vehicleIteration++) {
                Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "Audi",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
                parkingLotSystem.park(vehicle);
            }
            vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
            Vehicle vehicle1 = new Vehicle("MH10BQ8108", "BULLET",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle1);
            Vehicle vehicle2 = new Vehicle("MH10BQ8109", "Marshal",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
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
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        parkingLotSystem.addObserver(airportSecurity);
        int numberOfCars = 9;
        for (int vehicleIteration = 0; vehicleIteration < numberOfCars; vehicleIteration++) {
            Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "Audi",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
        }
        vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Marshal",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle1);
        Assert.assertEquals("Full", airportSecurity.getParkingLotStatus());
        Assert.assertEquals("Full", owner.getParkingLotStatus());
    }

    //Test Case When The Parking Lot Has Space Again
    @Test
    public void givenAVehicles_WhenParkingLotHasSpaceAgain_ShouldInformToParkingLotOwner() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        vehicle = new Vehicle("MH13AN0808", "Bolero",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Marshal",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle1);
        Assert.assertEquals("Have Space", owner.getParkingLotStatus());
    }

    //test for attendant park car whenever owner want
    @Test
    public void givenVehicle_WhenOwnerWantAttendant_ShouldParkTheCar() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        vehicle = new Vehicle("MH13AN0808", "McLaren",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("MH10BQ8109", "Ferari",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle1);
    }

    //Test For Find The Vehicle Number
    @Test
    public void givenVehicle_WhenWantToFindCar_ShouldReturnGetNumberInParkingLot() throws ParkingLotSystemException {
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        int TotalNumberOfCars = 9;
        for (int vehicleIteration = 0; vehicleIteration < TotalNumberOfCars; vehicleIteration++) {
            Vehicle vehicle = new Vehicle(Integer.toString(vehicleIteration), "BMW",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
        }
        Vehicle vehicle2 = new Vehicle("55", "Thar",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle2);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle2);
        Assert.assertEquals("C03", numberInParkingLot);
    }

    //Test For Charging The Vehicle When Its Park
    @Test
    public void givenVehicleParkInLot_WhenCharge_ThenReturnTrue() {
        try {
            parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
            parkingLotSystem.createParkingLot();
            parkingLotSystem.addObserver(owner);
            Vehicle vehicle = new Vehicle("100", "Benz",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
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
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        int numberOfCars = 8;
        for (int index = 0; index < numberOfCars; index++) {
            Vehicle vehicle = new Vehicle(Integer.toString(index), "BMW",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
        }
        Vehicle vehicle2 = new Vehicle("55", "Thar",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("75", "Thar",new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.unPark(vehicle3);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle2);
        Assert.assertEquals("B03", numberInParkingLot);
    }

    @Test
    public void givenVehicle_WhenHandicapDriverWantAttendant_ShouldGiveNearestFreeSpace() throws ParkingLotSystemException {
        int numberOfCars = 9;
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        Vehicle vehicle2 = new Vehicle("55", "Thar", new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("75", "Thar", new Driver(Driver.DriverType.HANDICAP),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle3);
        for (int index = 0; index < numberOfCars; index++) {
            Vehicle vehicle = new Vehicle(Integer.toString(index), "BMW", new Driver(Driver.DriverType.NORMAL),Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
        }
        parkingLotSystem.unPark(vehicle3);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle3);
        Assert.assertEquals("B03", numberInParkingLot);
    }

    //Test To Direct Large Vehicles To The Lot Which Has Max Space
    @Test
    public void givenVehicle_WhenOwnerWantAttendant_ShouldDirectLargeVehicleToHighestNumberOfFreeSpace() throws ParkingLotSystemException {
        int numberOfCars = 8;
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        Vehicle vehicle2 = new Vehicle("5", "Alto", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("7", "tata prima", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.LARGE,Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle3);
        for (int index = 0; index < numberOfCars; index++) {
            Vehicle vehicle = new Vehicle(Integer.toString(index), "BMW", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL,Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
        }
        parkingLotSystem.unPark(vehicle3);
        String numberInParkingLot = parkingLotSystem.getMyCarParkingNumber(vehicle2);
        Assert.assertEquals("A01", numberInParkingLot);
    }

    //Test To Give Info Police Of All The White Vehicles
    @Test
    public void givenVehicles_WhenPoliceDepartmentWantAllWhiteCars_ShouldReturnLocationOfAllCars() throws ParkingLotSystemException {
        int numberOfCars = 9;
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot();
        parkingLotSystem.addObserver(owner);
        for (int index = 0; index < numberOfCars; index++) {
            Vehicle vehicle = new Vehicle(Integer.toString(index), "Toyota", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL, Vehicle.Color.WHITE);
            parkingLotSystem.park(vehicle);
        }
        Vehicle vehicle2 = new Vehicle("55", "Ace", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL, Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("75", "Ventura", new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL, Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle3);
        listForPoliceDepartment = parkingLotSystem.getRecordsByVehicleColorForPolice(Vehicle.Color.WHITE);
        Assert.assertEquals(9,listForPoliceDepartment.size());
        Set<Map.Entry<String, Vehicle>> mapSet = parkingLot.entrySet();
        Map.Entry<String, Vehicle> elementAt3 = (new ArrayList<Map.Entry<String, Vehicle>>(mapSet)).get(2);
        Assert.assertEquals(Vehicle.Color.WHITE,elementAt3.getValue().getColor());
        Assert.assertEquals("A03",elementAt3.getKey());
    }

    //Test For Sending Info About Blue Toyota Vehicle
    @Test
    public void givenVehicles_WhenPoliceDepartmentWantAllBlueToyotaCarsInformation_ShouldReturnLocationOfAllCars() throws ParkingLotSystemException {
        int numberOfCars = 9;
        parkingLotSystem = new ParkingLotSystem(owner, parkingLotHandler, parkingLot, availableLot);
        parkingLotSystem.createParkingLot(11, 4);
        parkingLotSystem.addObserver(owner);
        for (int index = 0; index < numberOfCars; index++) {
            Vehicle vehicle = new Vehicle("MH10-" + Integer.toString(index), Vehicle.Vehicle_Brand.TOYOTA, new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL, Vehicle.Color.BLUE);
            parkingLotSystem.park(vehicle);
        }
        Vehicle vehicle2 = new Vehicle("MH10-55", Vehicle.Vehicle_Brand.HONDA, new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL, Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("MH10-75", Vehicle.Vehicle_Brand.TOYOTA, new Driver(Driver.DriverType.NORMAL), Vehicle.VehicleType.SMALL, Vehicle.Color.BLUE);
        parkingLotSystem.park(vehicle3);
        listForPoliceDepartment = parkingLotSystem.getRecordsByVehicleColorAndName(Vehicle.Vehicle_Brand.TOYOTA, Vehicle.Color.BLUE);
        Assert.assertEquals(10, listForPoliceDepartment.size());
        Set<Map.Entry<String, Slot>> mapSet = parkingLot.entrySet();
        Map.Entry<String, Slot> elementAt3 = (new ArrayList<Map.Entry<String, Slot>>(mapSet)).get(2);
        Assert.assertEquals(Vehicle.Color.BLUE, elementAt3.getValue().getVehicle().getColor());
        Assert.assertEquals("A03", elementAt3.getKey());
        Assert.assertEquals("MH46-7", elementAt3.getValue().getVehicle().getVehicleId());
        Assert.assertEquals("Attendant2", elementAt3.getValue().getAttendant().getName());
    }

}

