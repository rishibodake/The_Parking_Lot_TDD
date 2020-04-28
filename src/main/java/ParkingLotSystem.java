import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ParkingLotSystem {
    Attendant attendant = null;
    Owner owner = new Owner();
    LinkedHashMap<String, Vehicle> parkingLot = new LinkedHashMap();
    String isFull;
    int parkingLotSize = 2;
    private List<Observer> observerList = new ArrayList<>();

    public ParkingLotSystem(Owner owner, Attendant attendant) {
        this.owner = owner;
        this.attendant = attendant;
    }


    public void addObserver(Observer iObservable) {
            this.observerList.add(iObservable);
        }

          public void setStatus(String isFull) {
              this.isFull = isFull;
              for (Observer observer : this.observerList) {
                  observer.update(this.isFull);
              }
          }

        public void park(Vehicle vehicle) throws ParkingLotSystemException {
            boolean isAvailable = owner.isAvailable(parkingLot, parkingLotSize);
            if (isAvailable) {
                attendant.park(parkingLot, vehicle);
            }
            if (parkingLot.size() == parkingLotSize)
                setStatus("Full");
        }


        public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
            boolean isPresent = owner.isPresent(parkingLot, vehicle);
            if (isPresent) {
                attendant.unPark(parkingLot, vehicle);
            }
            if (parkingLot.size() < parkingLotSize)
                setStatus("Have Space");
        }

        public boolean isVehicleParked (Vehicle vehicle){
            if (parkingLot.containsKey(vehicle.getVehicleNumber()))
                return true;
            return false;
        }

        public boolean isVehicleUnParked (Vehicle vehicle){
            if (!parkingLot.containsKey(vehicle.getVehicleNumber()))
                return true;
            return false;
        }
    }


