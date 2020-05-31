<%@ page import="by.epam.ultraair.presentation.transfer.TicketDTO" %>
<%@ page import="by.epam.ultraair.persistence.domain.Flight" %>
<%@ page import="by.epam.ultraair.util.RestManagerUtil" %>
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
    <title>Ultra Air | Ticket order</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <style type="text/css">
        <%@include file="basic.css"%>
        main {
            display: table;
        }

        #order {
            width: 50%;
            max-width: 350px;
            text-align: center;
            border: 1px ridge gray;
            background: white;
            margin: auto;
            padding: 10px 0 10px 0;
        }
        #order input {
            border: 2px ridge gray;
            padding: 0 10px 0 10px;
            background: white;
            width: 50%;
            border-radius: 5px;
            margin: 6px 0 6px 0;
            height: 45px;
        }
        #order #submit {
            background: black;
            color: white;
        }
        #order #submit:hover {
            background: dimgray;
        }
    </style>
</head>
<body>
<main>
<div style="display: table-cell;vertical-align: middle;">
    <!-- Greetings -->
    <div style="text-align:center; margin-top: -25px">
        <h1>Ultra Air</h1>
        <p>Ticket ordering</p>
    </div>

    <!-- Ticket output -->
    <div>
        <%
            // get Ticket and sum, that will be show if ticket summary
            TicketDTO ticket = (TicketDTO) request.getAttribute("ticket");
            double sum = (double)request.getAttribute("sum");
            if (ticket == null) {
                request.getRequestDispatcher("LogIn").forward(request,response);
                return;
            }
        %>
        <form id="order" autocomplete="off" action="Order" enctype="application/x-www-form-urlencoded" method="post">
            <p>
                <%
                    Flight flight = RestManagerUtil.getFlight(ticket.flightID);
                    out.print("<b style=\"Font-Size: 16pt;\">New Ticket</b><br>");
                    out.print("Username = <b>" + ticket.clientName + "</b><br>");
                    out.print("Flight Num = <b>" + ticket.flightID + "</b><br>");
                    out.print("First on board = <b>" + ticket.wantRightFirstSitting + "</b><br>");
                    out.print("First on registration = <b>" + ticket.wantRightFirstRegistration + "</b><br>");
                    out.print("Departure date = <b>" + flight.getDepartureDate()  + "</b><br>");
                    out.print("<br>Ticket price = <b>" + sum + "</b>");
                %>
            </p>
            <input style="display: none;" type="hidden" name="toOrder" value="yes">
            <input id="submit" type="submit" value="Order">
        </form>
        <p style="text-align: center"><a href="Home">Back to home page</a></p>
        <%
            // save ticket to session
            session.setAttribute("ticket", ticket);
        %>
    </div>
</div>
</main>
</body>
</html>