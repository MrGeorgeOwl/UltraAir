<%@ page import="by.epam.ultraair.presentation.dto.TicketDTO" %><%--
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
        body{
            font-family: "Open Sans Light", serif;
            margin: 0;
            padding: 0;
            background: white;
        }
        main {
            display: table;
            margin: 0 auto;
            padding: 1% 2%;
            width: 70%;
            min-height: 95vh;
            background: whitesmoke;
        }
        h1 {
            font-size: 18pt;
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
    <div style="text-align:center; margin-top: -25px">
        <h1>Ultra Air</h1>
        <p>Ticket ordering</p>
    </div>
    <div>
        <%
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
                    out.print("<h2 style=\"margin-top: -15px;margin-bottom: 5px;\">Ticket</h2>");
                    out.print("Username = <b>" + ticket.clientName + "</b><br>");
                    out.print("Flight Num = <b>" + ticket.flightID + "</b><br>");
                    out.print("First on board = <b>" + ticket.wantRightFirstSitting + "</b><br>");
                    out.print("First on registration = <b>" + ticket.wantRightFirstRegistration + "</b><br>");
                    out.print("<br>Ticket price = <b>" + sum + "</b>");
                %>
            </p>
            <input style="display: none;" type="hidden" name="toOrder" value="yes">
            <input id="submit" type="submit" value="Order">
        </form>
        <%
            session.setAttribute("ticket", ticket);
        %>
    </div>
</div>
</main>
</body>
</html>