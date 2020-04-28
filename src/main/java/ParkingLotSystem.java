import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLotSystem {
    Attendant attendant = null;
    Owner owner = new Owner();
    HashMap<String, Object> parkingLot = new HashMap();
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
            if (parkingLot.size() < parkingLotSize)
            {
                parkingLot.put(vehicle.getVehicleNumber(), vehicle);
            }
            else if (parkingLot.size() == parkingLotSize)
            {
                throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot Is Full");
            }
            if (parkingLot.size() == parkingLotSize)
                setStatus("Full");
        }


        public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
            if (vehicle == null)
                throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE, "No Such Vehicle Found");
            else if (parkingLot.containsKey(vehicle.getVehicleNumber()))
                parkingLot.remove(vehicle.getVehicleNumber());
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


