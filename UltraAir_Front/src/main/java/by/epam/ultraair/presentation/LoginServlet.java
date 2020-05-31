package by.epam.ultraair.presentation;

import by.epam.ultraair.RestManagerUtil;
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
public class LoginServlet extends HttpServlet {
    static public Logger log = LogManager.getLogger(LoginServlet.class.getSimpleName());

    @Override
    // Login user in system
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        if (!RestManagerUtil.isUserExists(user)) {
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
        response.sendRedirect("Home");
    }

    @Override
    // Logout method
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getParameter("logout") != null){
            HttpSession session = request.getSession();
            session.removeAttribute("user");
        }
        // Send user back to home
        response.sendRedirect("Home");
    }
}
