public class ParkingLotSystemException extends Exception {
    enum ExceptionType {
        PARKING_LOT_IS_FULL,NO_SUCH_A_VEHICLE,PARKING_LOT_IS_FULL_FOR_LARGE_VEHICLE;
    }
    ExceptionType exceptionType;

    public ParkingLotSystemException(ExceptionType exceptionType,String massage){
        super(massage);
        this.exceptionType = exceptionType;
    }

}
