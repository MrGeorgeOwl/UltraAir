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
    // Get method being called when user click on "Order" button on one of the flights of index.jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user still logged
        HttpSession session = request.getSession();
        if (session.isNew() || session.getAttribute("user") == null) {
            request.getRequestDispatcher("LogIn").forward(request, response);
            return;
        }

        // Send user to OrderPage
        request.getRequestDispatcher("OrderPage").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*Case when user ordered the ticket and wants to know final price*/
        String getOrderPrice = request.getParameter("getOrderPrice");
        if (getOrderPrice != null && getOrderPrice.equals("yes")) {
            // get all inputted ticket parameters from order.jsp
            String user = (String) request.getSession().getAttribute("user");
            int flightNum = 0;
            try {
                flightNum = Integer.parseInt(request.getParameter("flightNum"));
            } catch (NumberFormatException ignored) {
            }
            boolean firstOnRegistration = "on".equals(request.getParameter("registration"));
            boolean firstOnBoard = "on".equals(request.getParameter("onboard"));

            // if there are problems with getting flight, then user isn't logged
            if (user == null || flightNum == 0) {
                // set error message
                request.setAttribute("logResult", "Incorrect Username or Password");
                // send user back with error message
                request.getRequestDispatcher("OrderPage").forward(request, response);
                return;
            }

            // create ticket to transfer
            TicketDTO ticket = createTicket(flightNum, user, firstOnRegistration, firstOnBoard);
            double sum = new TicketService().getTicketPrice(ticket);

            // set ticket and sum attributes to get them on the order_summary.jsp
            request.setAttribute("ticket", ticket);
            request.setAttribute("sum", sum);
            // send user to order_summary.jsp
            request.getRequestDispatcher("OrderSummary").forward(request, response);
            return;
        }

        /*Case when user agreed with price on order_summary.jsp*/
        String toOrder = request.getParameter("toOrder");
        if (toOrder != null && toOrder.equals("yes") ) {
            // Remove attribute to not make order again
            request.removeAttribute("toOrder");
            // Create new ticket in database
            orderTicket((TicketDTO)request.getSession().getAttribute("ticket"));
            // Send user back to home page index.jsp
            request.getRequestDispatcher("Home").forward(request,response);
        }
    }

    // make TicketDTO object from given fields
    private TicketDTO createTicket(int flightNum, String user, boolean registration, boolean board) {
        TicketDTO ticket = new TicketDTO();
        // get flight from list
        Flight flight = new FlightService().getFlights().get(flightNum - 1);

        ticket.flightID = flight.getId();
        ticket.clientName = user;
        ticket.wantRightFirstRegistration = registration;
        ticket.wantRightFirstSitting = board;

        return ticket;
    }

    // create new ticket in database
    private void orderTicket(TicketDTO ticket) {
        TicketService service = new TicketService();
        try {
            service.createUserTicket(ticket);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
