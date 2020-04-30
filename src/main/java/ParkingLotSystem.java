import java.util.*;

public class ParkingLotSystem {
    Attendant attendant = null;
    Owner owner = new Owner();
    LinkedHashMap<String, Vehicle> parkingLot = new LinkedHashMap();
    String isFull;
    int parkingLotSize = 10;
    private List<Observer> observerList = new ArrayList<>();
        //Constructor With Parameter
         public ParkingLotSystem(Owner owner, Attendant attendant) {
        this.owner = owner;
        this.attendant = attendant;
         }
        //Default Constructor
         public ParkingLotSystem() {

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
                boolean isAvailable = owner.isAvailable(parkingLot, parkingLotSize);
                if (isAvailable) {
                    attendant.park(parkingLot, vehicle);
                }
                if (parkingLot.size() == parkingLotSize)
                    setStatus("Full");
          }

          //Function That unpark The given vehicle from The Parking Lot
          public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
                boolean isPresent = owner.isPresent(parkingLot, vehicle);
                if (isPresent) {
                    attendant.unPark(parkingLot, vehicle);
                }
                if (parkingLot.size() < parkingLotSize)
                    setStatus("Have Space");
            }

           //Function That check whether the given vehicle is Parked in parkingLot
             public boolean isVehicleParked (Vehicle vehicle){
                if (parkingLot.containsKey(vehicle.getVehicleNumber()))
                    return true;
                return false;
             }

            //Function That check whether the given vehicle is Parked in parkingLot
            public boolean isVehicleUnParked (Vehicle vehicle){
                if (!parkingLot.containsKey(vehicle.getVehicleNumber()))
                    return true;
                return false;
            }

            public int getMyCarParkingNumber(Vehicle vehicle) {
                    Set<String> keys = parkingLot.keySet();
                    List<String> listKeys = new ArrayList<String>(keys);
                    return listKeys.indexOf(vehicle.getVehicleNumber());
                }
    }


