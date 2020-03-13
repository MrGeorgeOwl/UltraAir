package main.java.Service;

public class Luggage {
    private double weight;
    private Client client;
    private boolean overweight;

    public double getWeight() {
        return weight;
    }

    public Client getClient() {
        return client;
    }

    public boolean overweight() {
        return overweight;
    }

    public Luggage(){
        this.weight = 0;
        this.client = null;
        this.overweight = false;
    }

    public Luggage(double weight, Client client){
        this.weight = weight;
        this.client = client;
        this.overweight = isOverweight(weight);
    }

    private boolean isOverweight(double weight){
        return weight <= Constants.MAX_WEIGHT;
    }

    // TODO: Create service that allows find luggage by its client
}
