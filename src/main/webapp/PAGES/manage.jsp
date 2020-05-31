<%@ page import="by.epam.ultraair.persistence.service.UserService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.ultraair.persistence.domain.Flight" %>
<%@ page import="by.epam.ultraair.persistence.service.FlightService" %>
<%@ page import="by.epam.ultraair.presentation.ManageServlet" %><%--
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
        <%@include file="basic.css"%>

        .flights {
            text-align: center;
            margin: auto;
            width: 75%;
        }
        .flights table {
            margin: auto;
            border-bottom: 2px ridge gray;
            border-top: 2px ridge gray;
        }
        .flights td {
            border-left: 1px ridge black;
            text-align: center;
            vertical-align: middle;
            padding: 0 5px 0 5px;
        }
        .flights td:nth-last-child(1) {
            border-right: 2px ridge gray;
        }
        .flights td:nth-child(1) {
            border-left: 2px ridge gray;
        }
        .flights tr:nth-child(odd){
            background: white;
        }
        .flights tr:nth-child(even){
            background: whitesmoke	;
        }

        input {
            border: 1px ridge gray;
            background: white;
            border-radius: 5px;
        }
        #submit {
            background: black;
            color: white;
        }
        #submit:hover {
            background: dimgray;
        }

        .edit {
            width: 50%;
            max-width: 350px;
            text-align: center;
            border: 1px ridge gray;
            background: white;
            margin: 25px auto 0 auto;
            padding: 10px 0 10px 0;
        }
        .edit input {
            border: 2px ridge gray;
            padding: 0 10px 0 10px;
            width: 75%;
            margin: 6px 0 6px 0;
            height: 35px;
        }
        .edit #readonly {
            background: lightgray;
        }
    </style>
</head>
<body>
<main>
    <%
        String user = (String) session.getAttribute("user");
        try {
            if (user == null || !(new UserService().isAdmin(user))){
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect("LogIn");
        }
    %>
    <!-- Greeting -->
    <div style="text-align:center; margin-top: -25px;">
        <h1>Ultra Air</h1>
        <p>Flight managing | <a href="Home">Home</a></p>
    </div>
    <hr>

    <!-- Flights -->
    <div class="flights">
        <p style="font-size: 14pt; margin: 0"><b>All flights</b></p>
        <table>
            <thead>
                <tr style="background: lightgray;">
                    <td><b>id</b></td>
                    <td><b>from</b></td>
                    <td><b>to</b></td>
                    <td><b>departure</b></td>
                    <td><b>arrival</b></td>
                    <td><b>manage</b></td>
                </tr>
            </thead>
            <%
                //flights table
                ArrayList<Flight> flights = new FlightService().getFlights();
                for(Flight flight: flights) {
                    out.print("<tr>");
                    out.print("<td>" + flight.getId() + "</td>");
                    out.print("<td>" + flight.getFromPlace() + "</td>");
                    out.print("<td>" + flight.getToPlace() + "</td>");
                    out.print("<td>" + flight.getDepartureDate() + "</td>");
                    out.print("<td>" + flight.getArrivalDate() + "</td>");
                    out.print("<td>");
                    out.print(ManageServlet.createPostForm("delete", flight.getId(), "Delete"));
                    out.print(ManageServlet.createPostForm("goToEdit", flight.getId(), "Edit"));
                    out.print("</td>");
                    out.print("</tr>");
                }
                out.print("<td colspan=\"6\" style=\"border-top: 1px ridge gray;");
                out.print("padding: 3px 0 3px 0;\">");
                out.print(ManageServlet.createPostForm("goToAdd", 0, "+ Add New Flight"));
                out.print("</td>");
            %>
        </table>
        <div class="edit">
            <form action="Manage" enctype="application/x-www-form-urlencoded" method="post">
                <p style="font-size: 14pt; margin: 0"><b>Flight editing</b></p>
                <label>
                    <input id="readonly" type="text" name="id" value="<%
                        String id = (String) request.getAttribute("formID");
                        out.print((id != null) ? id : "New ID");
                    %>" readonly>
                </label>
                <label>
                    <input type="text" name="from" placeholder="From">
                </label>
                <label>
                    <input type="text" name="to" placeholder="To">
                </label>
                <label>
                    <input type="datetime-local" name="departure" placeholder="Data">
                </label>
                <label>
                    <input type="datetime-local" name="arrival" placeholder="Arrival">
                </label>
                <input type="hidden" name="action" value="<%
                    String action = (String) request.getAttribute("formAction");
                    out.print((action != null) ? action : "add");
                %>">
                <input id="submit" type="submit" value="<%
                    String submitText = (String) request.getAttribute("formSubmitText");
                    out.print((submitText != null) ? submitText : "Add New");
                %>">
                <%
                    if (request.getAttribute("logResult") != null) {
                        out.print("<br><b>" + request.getAttribute("logResult") + "</b>");
                    }
                %>
            </form>
        </div>
    </div>

</main>
</body>
</html>