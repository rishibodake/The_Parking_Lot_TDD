import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public void assignAttendantForParkingLot(int numberOfSlot) {
        this.numberOfSlot = numberOfSlot;
        for (int index = 0; index < numberOfSlot; index++) {
            attendants.add(new Attendant("Attendant" + Integer.toString(index + 1)));
        }
    }

    public Attendant getAttendant() {
        return attendants.get((int) (Math.random() * numberOfSlot));
    }}
