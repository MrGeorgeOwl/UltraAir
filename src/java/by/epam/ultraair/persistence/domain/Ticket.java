package by.epam.ultraair.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity{
    @Column(name = "userID")
    private Integer userID;

    @Column(name = "flightID")
    private Integer flightID;

    @Column(name = "price")
    private double price;

    @Column(name = "rightFirstRegistration")
    private boolean rightFirstRegistration;
    @Column(name = "rightFirstSitting")
    private boolean rightFirstSitting;


    public Ticket(Integer userId, Integer flightId, boolean rightFirstRegistration, boolean rightFirstSitting){
        this.userID = userId;
        this.flightID = flightId;
        this.rightFirstRegistration = rightFirstRegistration;
        this.rightFirstSitting = rightFirstSitting;
        calculatePrice();
    }

    public Ticket(Integer id, Integer userId, Integer flightId, boolean rightFirstRegistration, boolean rightFirstSitting, double price){
        super.setId(id);
        this.userID = userId;
        this.flightID = flightId;
        this.rightFirstRegistration = rightFirstRegistration;
        this.rightFirstSitting = rightFirstSitting;
        this.price = price;
    }

    public int getUserId() {
        return userID;
    }

    public void setUserId(int userID) {
        this.userID = userID;
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

    public int getFlightID() {
        return flightID;
    }

    public void setFlight(int flightID) {
        this.flightID = flightID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void calculatePrice(){
        double price = 200.;
        price = rightFirstRegistration ? price + 20.: price;
        price = rightFirstSitting ? price + 15.5: price;

        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "userID=" + userID +
                ", flightID=" + flightID +
                ", price=" + price +
                ", rightFirstRegistration=" + rightFirstRegistration +
                ", rightFirstSitting=" + rightFirstSitting +
                '}';
    }
}
