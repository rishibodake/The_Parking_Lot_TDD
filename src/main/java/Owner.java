public class Owner implements Observer {
    private String isFull;

    //overided method update from Observer Interface
    @Override
    public void update(Object status) {
        this.setIsFull((String) status);
    }
    //getter
    public void setIsFull(String isFull) {
        this.isFull = isFull;
    }

    public String getIsFull() {
        return isFull;
    }
}
