package DAO;

public class TicketEntity {
    private int flightId;
    private double price;
    private boolean rightFirstSitting;
    private boolean rightFirstRegistration;

    public TicketEntity(){
        this.flightId = 0;
        this.price = 0.0;
        this.rightFirstSitting = false;
        this.rightFirstRegistration = false;
    }

    public TicketEntity(int flightId, double price, boolean rightFirstSitting, boolean rightFirstRegistration) {
        this.flightId = flightId;
        this.price = price;
        this.rightFirstSitting = rightFirstSitting;
        this.rightFirstRegistration = rightFirstRegistration;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRightFirstSitting() {
        return rightFirstSitting;
    }

    public void setRightFirstSitting(boolean rightFirstSitting) {
        this.rightFirstSitting = rightFirstSitting;
    }

    public boolean isRightFirstRegistration() {
        return rightFirstRegistration;
    }

    public void setRightFirstRegistration(boolean rightFirstRegistration) {
        this.rightFirstRegistration = rightFirstRegistration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"flightId\" : ").append(flightId).append(",\n");
        sb.append("\"price\" : ").append(price).append(",\n");
        sb.append("\"rightFirstSitting\" : ").append(rightFirstSitting).append(",\n");
        sb.append("\"rightFirstRegistration\" : ").append(rightFirstRegistration).append("\n");
        return sb.toString();
    }
}
