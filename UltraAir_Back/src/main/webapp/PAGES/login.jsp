<%@ page import="by.epam.ultraair.util.RestManagerUtil" %><%--
  Created by IntelliJ IDEA.
  User: timoh
  Date: 24.02.2020
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ultra Air | Log In</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <style type="text/css">
        <%@include file="basic.css"%>
        main {
            display: table;
        }

        #login {
            width: 50%;
            max-width: 600px;
            text-align: center;
            margin: auto;
        }
        #login input {
            border: 2px ridge gray;
            padding: 0 10px 0 10px;
            background: white;
            width: 50%;
            border-radius: 5px;
            margin: 6px 0 6px 0;
            height: 45px;
        }
        #login #submit {
            background: black;
            color: white;
        }
        #login #submit:hover {
            background: dimgray;
        }
    </style>
</head>
<body>
<main>
<div style="display: table-cell;vertical-align: middle;">
    <!-- Greetings -->
    <div style="text-align:center; margin-top: -25px">
        <h1>Log in Ultra Air System</h1>
        <p>Easy buy - easy fly!</p>
    </div>

    <!-- Form Output -->
    <div>
        <form id="login" action="Login" enctype="application/x-www-form-urlencoded" method="post">
            <label>
                <input type="text" name="user" placeholder="Login">
            </label>
            <label>
                <input type="password" name="pass" placeholder="Password">
            </label>
            <input id="submit" type="submit" value="Log In">
            <%
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