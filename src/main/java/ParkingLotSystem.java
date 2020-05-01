import java.util.*;

public class ParkingLotSystem {
     LinkedHashMap availableLot = null ;
    Attendant attendant = null;
    Owner owner = new Owner();
    LinkedHashMap<String, Vehicle> parkingLot = new LinkedHashMap();
    String isFull;
    int parkingLotSize = 10;
    private List<Observer> observerList = new ArrayList<>();
    private int numberOfSlot = 1;

    //Constructor With Parameter
         public ParkingLotSystem(Owner owner, Attendant attendant) {
        this.owner = owner;
        this.attendant = attendant;
         }
        //Default Constructor
         public ParkingLotSystem() {

         }

    public ParkingLotSystem(Owner owner, Attendant attendant, LinkedHashMap<String, Vehicle> parkingLot,LinkedHashMap availableLot) {
        this.owner = owner;
        this.attendant = attendant;
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
              String getKey = owner.isAvailable(parkingLot, parkingLotSize);
              attendant.park(parkingLot, vehicle, getKey);

              if (!parkingLot.containsValue(null))
                    setStatus("Full");
          }

          //Function That unpark The given vehicle from The Parking Lot
          public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
              String getKey = owner.isPresent(parkingLot, vehicle);
              attendant.unPark(parkingLot, getKey);
              if (parkingLot.containsValue(null)) {
                  setStatus("Have Space");
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

    public void createParkingLot() {
        int counter = 1, index = 0, slot = 1, length = 0, slotCapacity = 0;
        while (index != parkingLotSize) {
            slotCapacity = parkingLotSize / numberOfSlot;
            if (parkingLotSize % numberOfSlot != 0)
                slotCapacity += 1;
            if (counter == slotCapacity + 1) {
                counter = 1;
                slot++;
            }
            String number1 = Integer.toString(counter);
            length = number1.length();
            if (length == 1) {
                number1 = "0" + number1;
            }
            String key = "P" + Integer.toString(slot) + number1;
            parkingLot.put(key, null);
            index++;
            counter++;
        }
    }
}


