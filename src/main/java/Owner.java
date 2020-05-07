import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Owner implements Observer {
    private String parkingLotStatus;
    private String parkingCharge;

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



    public void setParkingCharge(String parkingCharge) {
        this.parkingCharge = parkingCharge;
    }
}
