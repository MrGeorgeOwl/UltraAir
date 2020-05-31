package by.epam.ultraair.domain;

public class Luggage {
    private int id;
    private double weight;
    private Integer clientID;
    private boolean overweight;

    public Luggage(int id, double weight, Integer clientID){
        this.id = id;
        this.weight = weight;
        this.clientID = clientID;
        this.overweight = isOverweight(weight);
    }

    public int getId(){
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public Integer getClient() {
        return clientID;
    }

    public boolean overweight() {
        return overweight;
    }

    private boolean isOverweight(double weight){
        return weight <= 100;
    }

    @Override
    public String toString() {
        return "Luggage{" +
                "id=" + id +
                ", weight=" + weight +
                ", clientID=" + clientID +
                ", overweight=" + overweight +
                '}';
    }
}
