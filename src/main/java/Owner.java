public class Owner implements Observer {
    private String isFull;

    @Override
    public void update(Object status) {
        this.setIsFull((String) status);
    }

    public String getIsFull() {
        return isFull;
    }

    public void setIsFull(String isFull) {
        this.isFull = isFull;
    }
}