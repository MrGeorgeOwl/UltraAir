package by.epam.ultraair.presentation.transfer;

public class TicketDTO {
    public String clientName;
    public Integer flightID;
    public boolean wantRightFirstSitting;
    public boolean wantRightFirstRegistration;

    public TicketDTO(){
        clientName = "";
        flightID = 0;
        wantRightFirstSitting = false;
        wantRightFirstRegistration = false;
    }

    @Override
    public String toString() {
        return "Ticket {\n" +
                "clientName = " + clientName + ";\n" +
                "flightID = " + (flightID + 1) + ";\n" +
                "wantRightFirstSitting = " + ((wantRightFirstSitting) ? "yes" : "no") + ";\n" +
                "wantRightFirstRegistration = " + ((wantRightFirstRegistration) ? "yes" : "no") + ";\n" +
                "}";
    }
}
