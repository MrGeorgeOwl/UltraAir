package by.epam.ultraair.presentation.web.servlet;

import by.epam.ultraair.persistence.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {
    static public Logger log = LogManager.getLogger(Login.class.getSimpleName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        UserService userService = new UserService();
        try {
            log.debug(user);
            userService.isAdmin(user);
        } catch (Exception e) {
            log.warn("Unsuccessful try to log in");
            request.setAttribute("logResult", "Incorrect Username or Password");
            request.getRequestDispatcher("PAGES/login.jsp").forward(request,response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("pass", pass);
        log.debug(request.getContextPath());
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
