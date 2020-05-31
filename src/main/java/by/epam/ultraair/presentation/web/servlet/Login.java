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
    // Login user in system
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        UserService userService = new UserService();
        try {
            // If user doesn't exist, an exception is returned
            userService.isAdmin(user);
        } catch (Exception e) {
            log.warn("Unsuccessful try to log in");
            // Output of error on login page
            request.setAttribute("logResult", "Incorrect Username or Password");
            // Send user back to login page
            request.getRequestDispatcher("LogIn").forward(request,response);
            return;
        }

        // Save password and user to session memory
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("pass", pass);
        // Send logged user to home page
<<<<<<< HEAD
        request.getRequestDispatcher("Home").forward(request,response);
=======
        response.sendRedirect("Home");
>>>>>>> devServlets
    }

    @Override
    // Logout method
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("logout") != null){
            HttpSession session = request.getSession();
            session.removeAttribute("user");
        }
        // Send user back to home
<<<<<<< HEAD
        request.getRequestDispatcher("Home").forward(request,response);
=======
        response.sendRedirect("Home");
>>>>>>> devServlets
    }
}
