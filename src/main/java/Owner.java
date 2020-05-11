import java.util.LinkedHashMap;
import java.util.List;

public class Owner implements Observer {
    private List<Attendant> attendants;
    private String parkingLotStatus;
    private int numberOfSlot;

    public Owner(List<Attendant> attendants) {
        this.attendants = attendants;
    }

    //overided method update from Observer Interface
    @Override
    public void update(Object status) {
        this.setParkingLotStatus((String) status);
    }
    //getter
    public void setParkingLotStatus(String parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }

    public String getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void assignAttendantForParkingLot(int numberOfSlot,LinkedHashMap<String, Integer> availableLot) {
        this.numberOfSlot = numberOfSlot;
        for (int index = 0; index < numberOfSlot; index++) {
            String firstKey = availableLot.keySet().toArray()[index].toString();
            attendants.add(new Attendant("attendant_" + firstKey));
        }
    }

    public Attendant getAttendant(String keyAvailable) {
        for (int index = 0; index < attendants.size(); index++) {
            if (attendants.get(index).getName().contains(keyAvailable))
                return attendants.get(index);
        }
        return null;
    }
}
