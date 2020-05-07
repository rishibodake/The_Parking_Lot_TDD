import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotSystem {
    LinkedHashMap availableLot = null ;
    Owner owner = new Owner();
    LinkedHashMap<String, Vehicle> parkingLot = new LinkedHashMap();
    String isFull;
    int parkingLotSize = 11;
    private List<Observer> observerList = new ArrayList<>();
    private int numberOfSlot = 4;
    ParkingLot parkingLotHandler = null;
    Map<String,Vehicle> listForPoliceDepartment = new HashMap<>();
    String lotName[] = new String[25];



    public ParkingLotSystem(Owner owner, ParkingLot parkingLotHandler, LinkedHashMap<String, Vehicle> parkingLot,LinkedHashMap availableLot) {
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
              parkingLotHandler.park(parkingLot, vehicle, availableLot);
              if (!parkingLot.containsValue(null))
                    setStatus("Full");
          }

          //Function That unpark The given vehicle from The Parking Lot
          public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
              parkingLotHandler.unPark(parkingLot, vehicle, availableLot);
              if (parkingLot.containsValue(null)) {
                  setStatus("Have Space");
              }
          }

           //Function That check whether the given vehicle is Parked in parkingLot
             public boolean isVehicleParked (Vehicle vehicle){
                  if (parkingLot.containsValue(vehicle))
                      return true;
                  return false;
             }

            //Function That check whether the given vehicle is Parked in parkingLot
            public boolean isVehicleUnParked (Vehicle vehicle){
                if (!parkingLot.containsValue(vehicle))
                    return true;
                return false;
            }
            public String getMyCarParkingNumber(Vehicle vehicle) {

                Iterator<String> itr = parkingLot.keySet().iterator();
                while (itr.hasNext()) {
                    String key = itr.next();
                    if (parkingLot.get(key) == vehicle)
                        return key;
                }
                return null;
                }
    //This Function Is Responcible To Distribute The PArking Lot Evanly
    public void createParkingLot() {
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

    public Map<String, Vehicle> getRecordsByVehicleColorForPolice(Vehicle.Color color) {
        return listForPoliceDepartment = parkingLot.entrySet().stream().filter(entry -> color.equals(entry.getValue().getColor()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}


