import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotSystem {
    Owner owner = null;
    String isFull;
    private List<Observer> observerList = new ArrayList<>();
    ParkingLot parkingLotHandler = null;
    String lotName[] = new String[25];
    Slot slot = new Slot(null, null);
    LinkedHashMap<String, Slot> parkingLot = null;
    LinkedHashMap<String, Integer> availableLot = null;
    private List<Observable> observableList = new ArrayList<>();




    public ParkingLotSystem(Owner owner, ParkingLot parkingLotHandler, LinkedHashMap parkingLot,LinkedHashMap availableLot) {
        this.owner = owner;
        this.parkingLotHandler = parkingLotHandler;
        this.parkingLot = parkingLot;
        this.availableLot = availableLot;

         }

    //Function That Add Observer Into InterFace Observer
         public void addObserver(Observer iObservable) {
            this.observerList.add(iObservable);
         }
         //Function To SetThe Current Status Of ParkingLot
          public void setStatus(String isFull) {
              this.isFull = isFull;
              for (Observer observer : this.observerList) {
                  observer.update(this.isFull);
              }
          }

          //Function That park The Given Vehicle Into ParkingLot
          public void park(Vehicle vehicle) throws ParkingLotSystemException {
              if (isParkingLotFull()) {
                  throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking lot is full");
              }
              parkingLotHandler.park(parkingLot, vehicle, availableLot, slot, owner);
              if (isParkingLotFull())
                    setStatus("Full");
          }

          //Function That unpark The given vehicle from The Parking Lot
          public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
              if (vehicle == null) {
                  throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE, "No such a vehicle");
              }
              parkingLotHandler.unPark(parkingLot, vehicle, availableLot, slot);
              if (!isParkingLotFull()) {
                  setStatus("Have Space");
              }
          }

           //Function That check whether the given vehicle is Parked in parkingLot
             public boolean isVehicleParked (Vehicle vehicle){
                 Iterator it = parkingLot.keySet().iterator();
                 while (it.hasNext()) {
                     if ((parkingLot.get(it.next()).getVehicle()) == vehicle)
                         return true;
                 }
                  return false;
             }

            //Function That check whether the given vehicle is Parked in parkingLot
            public boolean isVehicleUnParked (Vehicle vehicle){
                Iterator it = parkingLot.keySet().iterator();
                while (it.hasNext()) {
                    if (((parkingLot.get(it.next()).getVehicle()) == vehicle))
                        return false;
                }
                return true;
            }

    public boolean isParkingLotFull() {
        Iterator it = parkingLot.keySet().iterator();
        while (it.hasNext()) {
            if ((parkingLot.get(it.next()).getVehicle()) == null) {
                return false;
            }
        }
        return true;
    }

            public String getMyCarParkingNumber(Vehicle vehicle) {

                Iterator<String> itr = parkingLot.keySet().iterator();
                while (itr.hasNext()) {
                    String key = itr.next();
                    if (vehicle.equals(parkingLot.get(key).getVehicle()))
                        return key;
                }
                return null;
                }
    //This Function Is Responcible To Distribute The PArking Lot Evanly
    public void createParkingLot(int parkingLotSize, int numberOfSlot) {
        owner.assignAttendantForParkingLot(numberOfSlot);
        int counter = 1, index = 0, slot = 1, length = 0, slotCapacity = 0;
        String letters = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
        lotName = letters.split(" ");
        while (index != parkingLotSize) {
            slotCapacity = parkingLotSize / numberOfSlot;
            if (parkingLotSize % numberOfSlot != 0)
                slotCapacity += 1;
            if (counter == slotCapacity + 1) {
                availableLot.put(lotName[slot - 1], counter - 1);
                counter = 1;
                slot++;
            }
            String number1 = Integer.toString(counter);
            length = number1.length();
            if (length == 1) {
                number1 = "0" + number1;
            }
            String key = lotName[slot - 1] + number1;
            parkingLot.put(key, null);
            index++;
            counter++;
            if (slot == numberOfSlot)
                availableLot.put(lotName[slot - 1], counter - 1);
        }
    }

    public Map<String, Slot> getRecordsByVehicleColorForPolice(Vehicle.Color color) {
        return parkingLot.entrySet().stream().filter(entry -> color.equals(entry.getValue().getVehicle().getColor()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Slot> getRecordsByVehicleColorAndName(Vehicle.Vehicle_Brand brand, Vehicle.Color color) {
        return parkingLot.entrySet().stream().filter(entry -> color.equals(entry.getValue().getVehicle().getColor()) && brand.equals(entry.getValue().getVehicle().getVehicle_brand()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}


