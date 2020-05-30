<%@ page import="by.epam.ultraair.persistence.service.FlightService" %>
<%@ page import="by.epam.ultraair.persistence.domain.Flight" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.ultraair.persistence.service.TicketService" %>
<%@ page import="by.epam.ultraair.persistence.domain.Ticket" %>
<%@ page import="javax.persistence.NoResultException" %>
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
        body{
            font-family: "Open Sans Light", serif;
            margin: 0;
            padding: 0;
            background: white;
        }
        main {
            margin: 0 auto;
            padding: 1% 2%;
            width: 70%;
            min-height: 95vh;
            background: whitesmoke;
        }
        h1 {
            font-size: 18pt;
        }

        .flights-container {
            display: flex;
            flex-direction: row;
            justify-content: space-evenly;
            flex-basis: 25%;
            flex-wrap: wrap;
        }
        .flights-container div {
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            margin: 5px;
            border: black solid 1px;
            background: white;
            width: 25%;
            min-height: 35vh;
            max-height: 42%;
        }
        .flights-container div p {
            color: gray;
        }
        .flights-container div input {
            border: 1px solid black;
            background: white;
            width: 65%;
            border-radius: 8px;
        }
        .flights-container div input:hover {
            background: whitesmoke;
        }
    </style>
</head>
<body>
<main>
    <div style="text-align:center; margin-top: -25px;">
        <h1>Flights</h1>
        <p>Easy buy - easy fly!</p>
    </div>

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
        <hr>
        <%
            ArrayList<Flight> flights = new FlightService().getFlights();
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
        %>
        <hr>
    </div>
    <hr>
    <h2 style="text-align:center;">Your Tickets</h2>
    <hr>
    <div class="flights-container">
        <hr>
        <%
            try {
                ArrayList<Ticket> tickets = new TicketService().getUserTickets(user);
                for (Ticket ticket : tickets) {
                    out.print("<div><span>");
                    out.print("<h2 style=\"margin-top: -15px;margin-bottom: 5px;\">Ticket</h2>");
                    out.print("Username = <b>" + user + "</b><br>");
                    out.print("Flight Num = <b>" + ticket.getFlightID() + "</b><br>");
                    out.print("First on board = <b>" + ticket.isRightFirstSitting() + "</b><br>");
                    out.print("First on registration = <b>" + ticket.isRightFirstRegistration() + "</b><br>");
                    out.print("<br>Ticket price = <b>" + ticket.getPrice() + "</b>");
                    out.print("</span></div>");
                }
            } catch(Exception ignored) {
                out.print("<p style=\"text-align:center;\">You have no tickets yet...");
                if (user.equals("Guest")) {
                    out.print("<br><a href=\"LogIn\">Log In</a> to order one!");
                }
                out.print("</p>");
            }
        %>
        <hr>
    </div>
    <hr>
</main>
</body>
</html>