<%@ page import="by.epam.ultraair.domain.Flight" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.ultraair.domain.Ticket" %>
<%@ page import="by.epam.ultraair.RestManagerUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: timoh
  Date: 24.02.2020
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ultra Air | Home</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <style type="text/css">
        <%@include file="PAGES/basic.css"%>
        input {
            border: 1px solid black;
            background: white;
            width: 65%;
            border-radius: 8px;
        }
        input:hover {
            background: whitesmoke;
        }

        .flights-container {
            display: flex;
            margin: auto;
            flex-direction: row;
            justify-content: space-evenly;
            flex-basis: 30%;
            flex-wrap: wrap;
            width: 75%;
        }
        .flights-container div {
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            margin: 5px;
            border: black solid 1px;
            background: white;
            width: 30%;
            min-height: 35vh;
            max-height: 45%;
        }
        .flights-container div p {
            color: gray;
        }
    </style>
</head>
<body>
<main>
    <!-- Greeting -->
    <div style="text-align:center; margin-top: -25px;">
        <h1>Flights</h1>
        <p>Easy buy - easy fly!</p>
    </div>

    <!-- Flights And Tickets Output -->
    <div>
        <div style="text-align:center;margin-bottom: 15px;">
            <%
                String user = (String) session.getAttribute("user");
                if (user == null || user.isEmpty()) {
                    user = "Guest";
                    out.print("Welcome, Guest! <a href=\"LogIn\">Log In</a> to order tickets.");
                }
                else {
                    out.print("Hello, <b>" + user + "</b>! You can ");
                    out.print("<a href=\"Login?logout=yes\">Logout</a> if you want.");
                }
            %>
        </div>

        <hr>
        <div class="flights-container">
            <%
                // output of available flights
                ArrayList<Flight> flights = RestManagerUtil.getAllFlights();
                for (int i = 0; i < flights.size(); i++) {
                    Flight flight = flights.get(i);
                    out.print("<div><span>");
                    out.print("<h2>" + flight.getFromPlace() + " - " + flight.getToPlace() + "</h2>");
                    out.print("<p style=\"margin-top: -20px;color: black;\">#" + flight.getId() + "<br></p>");

                    out.print("<p><b>Departure:</b><br>" + flight.getDepartureDate() + "<br><br>");
                    out.print("<b>Arrival:</b><br>" + flight.getArrivalDate() + "</p>");

                    out.print("<form action=\"Order\"" + "enctype=\"multipart/form-data\" method=\"get\">");
                    out.print("<input name=\"flight\" type=\"number\" value=" + (i + 1) + " style=\"display: none;\">");
                    out.print("<input type=\"submit\" value=\"Order\"></form></span></div>");
                }
                if (flights.size() == 0) {
                    out.print("There are temporarily no flights...");
                }
            %>
        </div>
        <hr>
        <h2 style="text-align:center;">Your Tickets</h2>
        <hr>
        <div class="flights-container">
            <%
                // output of user ordered tickets
                ArrayList<Ticket> tickets = RestManagerUtil.getUserTickets(user);
                for (Ticket ticket : tickets) {
                    Flight flight = RestManagerUtil.getFlight(ticket.getFlightID());
                    out.print("<div><span>");
                    out.print("<b style=\"Font-Size: 16pt;\">Ticket #" + ticket.getId() + "</b><br>");
                    out.print("Username = <b>" + user + "</b><br>");
                    out.print("Flight ID = <b>" + ticket.getFlightID() + "</b><br>");
                    out.print("First on board = <b>" + ticket.isRightFirstSitting() + "</b><br>");
                    out.print("First on registration = <b>" + ticket.isRightFirstRegistration() + "</b><br>");
                    out.print("Departure date = <b>" + flight.getDepartureDate() + "</b><br>");
                    out.print("<br>Ticket price = <b>" + ticket.getPrice() + "</b>");
                    out.print("</span></div>");
                }
                if (tickets.size() == 0) {
                    out.print("<p style=\"text-align:center;\">You have no tickets yet...");
                    if (user.equals("Guest")) {
                        out.print("<br><a href=\"LogIn\">Log In</a> to order one!");
                    }
                    out.print("</p>");
                }
            %>
        </div>
        <hr>
    </div>

    <!-- Managing page -->
    <div <%
        if (user.equals("Guest") || !(RestManagerUtil.isUserAdmin(user))){
            out.print("style=\"display: none;\"");
        }
    %>>
        <form style="text-align: center" action="Manage" enctype="application/x-www-form-urlencoded" method="get">
            Admins page:<br>
            <input style="max-width: 250px; height: 30px" type="submit" value="Enter Manage Page">
        </form>
    </div>
</main>
</body>
</html>