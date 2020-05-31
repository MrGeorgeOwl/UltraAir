<%@ page import="by.epam.ultraair.persistence.domain.Flight" %>
<%@ page import="by.epam.ultraair.persistence.service.FlightService" %>
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
            width: 75%;
            border-radius: 5px;
            margin: 6px 0 6px 0;
            height: 45px;
        }
        #order #readonly {
            background: lightgray;
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

    <!-- Order form -->
    <div>
        <form id="order" autocomplete="off" action="Order" enctype="application/x-www-form-urlencoded" method="post">
            <label>
                <%
                    // getting order info
                    String user = (String)session.getAttribute("user");
                    int flightNum = -1;
                    try {
                       flightNum = Integer.parseInt(request.getParameter("flight"));
                    } catch (Exception ignored){};
                    Flight flight = RestManagerUtil.getAllFlights().get(flightNum - 1);
                    // if one of info is invalid than user not logged
                    if (user == null || flightNum == -1){
                        // send user to login page
                        request.getRequestDispatcher("PAGES/login.jsp").forward(request,response);
                        return;
                    }

                    // print readonly fields
                    out.print("<br>Username:<br> <input id=\"readonly\""
                            +  "type=\"text\" readonly value=\"" + user + "\">");
                    out.print("<br>Flight:<br> <input id=\"readonly\" type=\"number\""
                            + "name=\"flightID\" readonly value=\"" + flight.getId() + "\">");
                %>
            </label>
            <label>
                <br>First on registration:<br>
                <input type="checkbox" name="registration" placeholder="Password">
            </label>
            <label>
                <br>First on board:<br>
                <input type="checkbox" name="onboard" placeholder="Password">
            </label>
                <input type="hidden" name="getOrderPrice" value = "yes">
            <input id="submit" type="submit" value="Order">
            <%
                // if user where returned to the page
                if (request.getAttribute("logResult") != null) {
                    out.print("<br><b>" + request.getAttribute("logResult") + "</b>");
                }
            %>
        </form>
        <p style="text-align: center"><a href="Home">Back to home page</a></p>
    </div>
</div>
</main>
</body>
</html>