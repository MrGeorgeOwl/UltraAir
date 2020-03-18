package service;

public class Client {
    private String name;
    private boolean haveLuggage;

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


    public Client(){
        name = "";
        haveLuggage = false;
    }

    public Client(String name, boolean haveLuggage){
        this.name = isNameCorrect(name) ? name : "";
        this.haveLuggage = haveLuggage;
    }

    private boolean isNameCorrect(String name){
        return name.startsWith(String.valueOf(name.toCharArray()[0]).toUpperCase());
    }
}
