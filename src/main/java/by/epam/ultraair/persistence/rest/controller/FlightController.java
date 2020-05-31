package by.epam.ultraair.persistence.rest.controller;


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
    public ResponseEntity<String> createFlight(@RequestBody Map<String, String> request) {
        String fromPlace = request.get("fromPlace");
        String toPlace = request.get("toPlace");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date departureDate;
        Date arrivalDate;
        try {
            String departureDateStr = request.get("departureDate");
            departureDateStr = departureDateStr.substring(0, 10) + " " + departureDateStr.substring(11, 19);
            departureDate = dateFormat.parse(departureDateStr);

            String arrivalDateStr = request.get("arrivalDate");
            arrivalDateStr = arrivalDateStr.substring(0, 10) + " " + arrivalDateStr.substring(11, 19);
            arrivalDate = dateFormat.parse(arrivalDateStr);
        }
        catch (ParseException e){
            e.printStackTrace();
            return new ResponseEntity<>("Error: wrong date format", HttpStatus.BAD_REQUEST);
        }

        new FlightDAOImpl().createFlight(new Flight(fromPlace, toPlace, departureDate, arrivalDate));
        return new ResponseEntity<>("Add Success", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> changeFlight(@RequestBody Map<String, String> request, @PathVariable Integer id) {
        Flight flight = new FlightDAOImpl().get(id).orElse(null);
        if (flight == null){
            return new ResponseEntity<>("Error: couldn't get flight with this id", HttpStatus.NOT_ACCEPTABLE);
        }

        String fromPlace = request.get("fromPlace");
        if (fromPlace != null){
            flight.setFromPlace(fromPlace);
        }

        String toPlace = request.get("toPlace");
        if (toPlace != null){
            flight.setToPlace(toPlace);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        try{
            String departureDateStr = request.get("departureDate");
            if (departureDateStr != null){
                departureDateStr = departureDateStr.substring(0, 10) + " " + departureDateStr.substring(11, 19);
                Date departureDate = dateFormat.parse(departureDateStr);
                flight.setDepartureDate(departureDate);
            }

            String arrivalDateStr = request.get("arrivalDate");
            if (arrivalDateStr != null){
                arrivalDateStr = arrivalDateStr.substring(0, 10) + " " + arrivalDateStr.substring(11, 19);
                Date arrivalDate = dateFormat.parse(arrivalDateStr);
                flight.setArrivalDate(arrivalDate);
            }
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        new FlightDAOImpl().updateFlight(flight);

        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Integer id){
        Flight flight = new FlightDAOImpl().get(id).orElse(null);

        if (flight == null){
            return new ResponseEntity<>("Error: couldn't get flight with this id", HttpStatus.NOT_ACCEPTABLE);
        }

        new FlightDAOImpl().deleteFlight(flight);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }

}
