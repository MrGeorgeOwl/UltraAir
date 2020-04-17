package by.epam.ultraair.presentation.dto;

public class TicketDTO {
    public String clientName;
    public boolean clientHaveLuggage;
    public double luggageWeight;
    public Integer flightID;
    public boolean wantRightFirstSitting;
    public boolean wantRightFirstRegistration;

    public TicketDTO(){
        clientName = "";
        clientHaveLuggage = false;
        luggageWeight = 0;
        flightID = 0;
        wantRightFirstSitting = false;
        wantRightFirstRegistration = false;
    }

    @Override
    public String toString() {
        return "Ticket {\n" +
                "clientName = " + clientName + ";\n" +
                "clientHaveLuggage = " + ((clientHaveLuggage) ? "yes" : "no") + ";\n" +
                "flightID = " + (flightID + 1) + ";\n" +
                "wantRightFirstSitting = " + ((wantRightFirstSitting) ? "yes" : "no") + ";\n" +
                "wantRightFirstRegistration = " + ((wantRightFirstRegistration) ? "yes" : "no") + ";\n" +
                "}";
    }
}
