package service.domain_objects;

import service.domain_objects.Client;
import service.domain_objects.Flight;

public class Ticket {
    private Client client;
    private Flight flight;
    private double price;
    private boolean rightFirstSitting;
    private boolean rightFirstRegistration;

    public Ticket(){
        client = null;
        flight = null;
        price = 0.;
        rightFirstSitting = false;
        rightFirstRegistration = false;
    }

    public Ticket(Client client, Flight flight, boolean rightFirstRegistration, boolean rightFirstSitting){
        this.client = client;
        this.flight = flight;
        this.rightFirstRegistration = rightFirstRegistration;
        this.rightFirstSitting = rightFirstSitting;
        calculatePrice();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private void calculatePrice(){
        double price = 0.;
        if (rightFirstSitting){
            price += 10;
        }
        if (rightFirstRegistration){
            price += 10;
        }
        price += flight.getTo().length() * 10;

        this.price = price;
    }
}
