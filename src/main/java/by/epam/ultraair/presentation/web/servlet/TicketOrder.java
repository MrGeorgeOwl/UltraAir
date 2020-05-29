package by.epam.ultraair.presentation.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OrderTicket")
public class TicketOrder extends HttpServlet {
    static public Logger log = LogManager.getLogger(TicketOrder.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.isNew() || session.getAttribute("user") == null) {
            request.getRequestDispatcher("PAGES/login.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
