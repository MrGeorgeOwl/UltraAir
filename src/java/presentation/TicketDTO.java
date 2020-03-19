package presentation;

public class TicketDTO {
    public String clientName;
    public boolean clientHaveLuggage;
    public int flightOrder;
    public boolean wantRightFirstSitting;
    public boolean wantRightFirstRegistration;

    @Override
    public String toString() {
        return "Ticket {\n" +
                "clientName = " + clientName + ";\n" +
                "clientHaveLuggage = " + ((clientHaveLuggage) ? "yes" : "no") + ";\n" +
                "flightID = " + (flightOrder + 1) + ";\n" +
                "wantRightFirstSitting = " + ((wantRightFirstSitting) ? "yes" : "no") + ";\n" +
                "wantRightFirstRegistration = " + ((wantRightFirstRegistration) ? "yes" : "no") + ";\n" +
                "}";
    }
}
