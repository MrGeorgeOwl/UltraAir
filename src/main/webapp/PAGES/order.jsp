<%@ page import="by.epam.ultraair.persistence.domain.Flight" %><%--
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
                    String user = (String)session.getAttribute("user");
                    String flightNum = (String)request.getParameter("flight");
                    if (user == null || flightNum == null){
                        request.getRequestDispatcher("PAGES/login.jsp").forward(request,response);
                    }
                    out.print("<br>Username:<br> <input id=\"readonly\""
                            +  "type=\"text\" readonly value=\"" + user + "\">");
                    out.print("<br>Flight:<br> <input id=\"readonly\" type=\"number\""
                            + "name=\"flightNum\" readonly value=\"" + flightNum + "\">");
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
            <input id="submit" type="submit" value="Order">
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