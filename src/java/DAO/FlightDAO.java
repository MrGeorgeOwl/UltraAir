package DAO;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Optional;

public class FlightDAO implements DAO<Flight> {

    Hashtable<Integer, Flight> flightTable;

    public FlightDAO() throws IOException, ParseException, java.text.ParseException {
        ClassLoader classLoader = FlightDAO.class.getClassLoader();
        File file = new File(classLoader.getResource("Flights.json").getFile());
        String path = file.getAbsolutePath();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray) jsonObject.get("flights");
        Iterator<Object> iterator = jsonArray.iterator();


        while (iterator.hasNext()) {
            JSONObject object = (JSONObject) jsonParser.parse(iterator.next().toString());
            String fromPlace = object.get("fromPlace").toString();
            String toPlace = object.get("toPlace").toString();

            String departureDateString = object.get("departureDate").toString();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Date departureDate = ft.parse(departureDateString);

            int passengersAmount = Integer.valueOf(object.get("passengersAmount").toString());
            int FlightDurationHours = Integer.valueOf(object.get("FlightDurationHours").toString());
            Integer id = Integer.valueOf(object.get("id").toString());
            flightTable.put(id, new Flight(fromPlace, toPlace, departureDate, passengersAmount, FlightDurationHours));
        }
    }

    @Override
    public Optional<Flight> get(Integer id) {
        return Optional.of(flightTable.get(id));
    }

    @Override
    public Hashtable<Integer, Flight> getAll() {
        return flightTable;
    }
}

//TODO: optimize this big parser depending on Flight structure (and JSON-file too!)