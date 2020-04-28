public class ParkingLotSystemException extends Exception {
    enum ExceptionType {
        PARKING_LOT_IS_FULL;
    }
    ExceptionType exceptionType;

    public ParkingLotSystemException(ExceptionType exceptionType,String massage){
        this.exceptionType = exceptionType;
    }

}
