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
import java.util.Optional;

public class FlightRepository implements DAO<FlightEntity> {

    Hashtable<Integer, FlightEntity> flightTable = new Hashtable<Integer, FlightEntity>();

    public FlightRepository() throws IOException, ParseException, java.text.ParseException {
        ClassLoader classLoader = FlightRepository.class.getClassLoader();
        File file = new File(classLoader.getResource("Flights.json").getFile());
        String path = file.getAbsolutePath();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray) jsonObject.get("flights");


        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) jsonParser.parse(o.toString());
            String fromPlace = object.get("fromPlace").toString();
            String toPlace = object.get("toPlace").toString();

            String departureDateString = object.get("departureDate").toString();
            SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy', 'hh:mm a");
            Date departureDate = ft.parse(departureDateString);

            String arrivalDateString = object.get("arrivalDate").toString();
            Date arrivalDate = ft.parse(arrivalDateString);

            int passengersAmount = Integer.parseInt(object.get("passengersAmount").toString());

            Integer id = Integer.valueOf(object.get("id").toString());
            flightTable.put(id, new FlightEntity(fromPlace, toPlace, departureDate, arrivalDate, passengersAmount));
        }
    }

    @Override
    public Optional<FlightEntity> get(Integer id) {
        return Optional.of(flightTable.get(id));
    }

    @Override
    public Hashtable<Integer, FlightEntity> getAll() {
        return flightTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        sb.append("\"flights\" : [\n");
        for (Integer key: flightTable.keySet()){
            sb.append("{\n");
            sb.append("\"id\" : ").append(key).append(",\n");
            sb.append(flightTable.get(key).toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }
}
