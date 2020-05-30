package by.epam.ultraair.api.flight.controller;


import by.epam.ultraair.dao.implementation.FlightDAOImpl;
import by.epam.ultraair.persistence.domain.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api/flight")
public class FlightController {

    @GetMapping
    public ResponseEntity<ArrayList<Flight>> getFlightList(){
        return ResponseEntity.ok(new FlightDAOImpl().getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable("id") Integer flightId){
        Optional<Flight> flightOptional = new FlightDAOImpl().get(flightId);
        Flight flight = flightOptional.orElse(null);
        if (flight != null){
            return ResponseEntity.ok(flight);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("create")
    public ResponseEntity<String> createFlight(@RequestBody Map<String, String> request) throws ParseException {
        String fromPlace = request.get("fromPlace");
        String toPlace = request.get("toPlace");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String departureDateStr = request.get("departureDate");
        departureDateStr = departureDateStr.substring(0, 10) + " " + departureDateStr.substring(11, 19);
        Date departureDate = dateFormat.parse(departureDateStr);

        String arrivalDateStr = request.get("arrivalDate");
        arrivalDateStr = arrivalDateStr.substring(0, 10) + " " + arrivalDateStr.substring(11, 19);
        Date arrivalDate = dateFormat.parse(arrivalDateStr);

        new FlightDAOImpl().createFlight(new Flight(fromPlace, toPlace, departureDate, arrivalDate));
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
