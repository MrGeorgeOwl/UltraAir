package by.epam.ultraair.presentation.web.servlet;

import by.epam.ultraair.persistence.domain.Flight;
import by.epam.ultraair.persistence.service.FlightService;
import by.epam.ultraair.persistence.service.TicketService;
import by.epam.ultraair.presentation.dto.TicketDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Order")
public class Order extends HttpServlet {
    static public Logger log = LogManager.getLogger(Order.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.isNew() || session.getAttribute("user") == null) {
            request.getRequestDispatcher("LogIn").forward(request, response);
            return;
        }

        request.getRequestDispatcher("OrderPage").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String toOrder = request.getParameter("toOrder");
            if (toOrder != null && toOrder.equals("yes") ) {
                request.removeAttribute("toOrder");
                orderTicket((TicketDTO) request.getSession().getAttribute("ticket"));
                request.getRequestDispatcher("Home").forward(request,response);
                return;
            }

            String user = (String)request.getSession().getAttribute("user");
            int weight = 0;
            int flightNum = 0;
            try {
                flightNum = Integer.parseInt(request.getParameter("flightNum"));
            } catch (NumberFormatException ignored) {}
            boolean firstOnRegistration = "on".equals(request.getParameter("registration"));
            boolean firstOnBoard = "on".equals(request.getParameter("onboard"));

            if (user == null || flightNum == 0) {
                request.setAttribute("logResult", "Incorrect Username or Password");
                request.getRequestDispatcher("OrderPage").forward(request,response);
                return;
            }

            TicketDTO ticket = createTicket(flightNum, user, weight, firstOnRegistration, firstOnBoard);
            double sum = new TicketService().getTicketPrice(ticket);

            request.setAttribute("ticket", ticket);
            request.setAttribute("sum", sum);
            request.getRequestDispatcher("OrderSummary").forward(request, response);
    }

    private TicketDTO createTicket(int flightNum, String user, int luggage, boolean registration, boolean board) {
        TicketDTO ticket = new TicketDTO();
        Flight flight = new FlightService().getFlights().get(flightNum - 1);

        ticket.flightID = flight.getId();
        ticket.clientName = user;
        ticket.wantRightFirstRegistration = registration;
        ticket.wantRightFirstSitting = board;

        return ticket;
    }

    private void orderTicket(TicketDTO ticket) {
        TicketService service = new TicketService();
        try {
            service.createUserTicket(ticket);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
