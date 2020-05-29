<%@ page import="by.epam.ultraair.persistence.service.FlightService" %>
<%@ page import="by.epam.ultraair.persistence.domain.Flight" %>
<%@ page import="java.util.ArrayList" %>
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
    <title>Ultra Air</title>
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

        #flights-container {
            display: flex;
            flex-direction: row;
            justify-content: space-evenly;
            flex-basis: 25%;
            flex-wrap: wrap;
        }
        #flights-container div {
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
        #flights-container div p {
            color: gray;
        }
        #flights-container div input {
            border: 1px solid black;
            background: white;
            width: 65%;
            border-radius: 8px;
        }
        #flights-container div input:hover {
            background: whitesmoke;
        }
    </style>
</head>
<body>
<main>
    <div style="text-align:center; margin-top: -25px">
        <h1>Flights</h1>
        <p>Easy buy - easy fly!</p>
    </div>

    <div id="flights-container">
        <%
            ArrayList<Flight> flights = new FlightService().getFlights();
            for (int i = 0; i < flights.size(); i++){
                Flight flight = flights.get(i);
                StringBuilder flightBuilder = new StringBuilder();
                flightBuilder.append("<div><span>")
                        .append("<h2>")
                        .append(flight.getFromPlace())
                        .append(" - ").append(flight.getToPlace())
                        .append("</h2>")
                        .append("<p style=\"margin-top: -20px;color: black;\">#")
                        .append(flight.getId()).append("<br></p>")

                        .append("<p><b>Departure:</b><br>")
                        .append(flight.getDepartureDate()).append("<br><br>")
                        .append("<b>Arrival:</b><br>")
                        .append(flight.getArrivalDate())
                        .append("</p>")

                        .append("<form action=\"OrderTicket\"")
                        .append("enctype=\"multipart/form-data\" method=\"get\">")
                        .append("<input name=\"flight\" type=\"number\" value=")
                        .append(i+1)
                        .append(" style=\"display: none;\">")
                        .append("<input type=\"submit\" value=\"Order\"></form>")

                        .append("</span></div>");
                out.print(flightBuilder.toString());
            }
        %>
    </div>
</main>
</body>
</html>