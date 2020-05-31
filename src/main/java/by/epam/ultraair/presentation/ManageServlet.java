package by.epam.ultraair.presentation;

import by.epam.ultraair.persistence.service.FlightService;
import by.epam.ultraair.presentation.transfer.FlightDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/Manage")
public class ManageServlet extends HttpServlet {
    static public Logger log = LogManager.getLogger(ManageServlet.class.getSimpleName());

    @Override
    // Get method usually being called when admin click on "Enter Manage Page" button
    // on the index.jsp. Just redirect user to the next page.
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("Managing");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        boolean success = true;
        switch(action) {
            case "goToAdd": {
                request.setAttribute("formAction", "add");
                request.setAttribute("formID", "New ID");
                request.setAttribute("formSubmitText", "Add New");
                break;
            }
            case "goToEdit": {
                String id = request.getParameter("flightID");
                request.setAttribute("formAction", "edit");
                request.setAttribute("formID", id);
                request.setAttribute("formSubmitText", "Edit Flight");
                break;
            }
            case "delete": {
                FlightService service = new FlightService();
                int flightID;
                try {
                    flightID = Integer.parseInt(request.getParameter("flightID"));
                } catch (NumberFormatException e) {
                    success = false;
                    break;
                }
                service.deleteFlight(service.getFlight(flightID));
                break;
            }
            case "add": {
                FlightDTO flight = new FlightDTO();
                flight.from = request.getParameter("from");
                flight.to = request.getParameter("to");
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    flight.departureDate = format.parse(request.getParameter("departure"));
                    flight.arrivalDate = format.parse(request.getParameter("arrival"));
                } catch (ParseException e) {
                    success = false;
                    break;
                }

                new FlightService().addFlight(flight);
                break;
            }
            case "edit": {
                FlightDTO flight = new FlightDTO();
                flight.from = request.getParameter("from");
                flight.to = request.getParameter("to");
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                int id;
                try {
                    flight.departureDate = format.parse(request.getParameter("departure"));
                    flight.arrivalDate = format.parse(request.getParameter("arrival"));
                    id = Integer.parseInt(request.getParameter("flightID"));
                } catch (ParseException | NumberFormatException e) {
                    success = false;
                    break;
                }

                new FlightService().updateFlight(id, flight);
                break;
            }
            default:
                request.setAttribute("logResult", "Editing error. Try again");
                log.error("Incorrect form call");
        }
        if (!success) {
            log.error("Editing error");
            request.setAttribute("logResult", "Editing error. Try again");
        }
        request.getRequestDispatcher("Managing").forward(request, response);
    }

    public static String createPostForm(String action, int flightID, String submitText) {
        StringBuilder builder = new StringBuilder();
        builder.append("<form action=\"Manage\" method=\"post\" ")
                .append("style=\"margin: 0;\"")
                .append("enctype=\"application/x-www-form-urlencoded\">")
                .append("<input name=\"action\" type=\"hidden\" value=\"")
                .append(action).append("\">")
                .append("<input name=\"flightID\" type=\"hidden\" value=\"")
                .append(flightID).append("\">")
                .append("<input id=\"submit\" type=\"submit\" value=\"")
                .append(submitText).append("\">")
                .append("</form>");
        return builder.toString();
    }
}
