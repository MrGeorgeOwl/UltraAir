package main.java.Service;

public class Client {
    private String name;
    private boolean haveLuggage;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHaveLuggage() {
        return haveLuggage;
    }

    public void setHaveLuggage(boolean haveLuggage) {
        this.haveLuggage = haveLuggage;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Client(){
        name = "";
        haveLuggage = false;
        money = 0.;
    }

    public Client(String name, boolean haveLuggage, double money){
        this.name = isNameCorrect(name) ? name : "";
        this.haveLuggage = haveLuggage;
        this.money = isMoneyCorrect(money) ? money : 0.;
    }

    private boolean isMoneyCorrect(double money){
        return money >= 0.;
    }

    private boolean isNameCorrect(String name){
        return name.startsWith(String.valueOf(name.toCharArray()[0]).toUpperCase());
    }
}
