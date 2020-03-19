package DAO;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Random;

public class FlightRepository implements DAO<Integer, FlightEntity> {

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

            SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy', 'hh:mm a");
            String departureDateString = object.get("departureDate").toString();
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
    public void save() throws IOException {
        ClassLoader classLoader = FlightRepository.class.getClassLoader();
        File file = new File(classLoader.getResource("Flights.json").getFile());
        String fileName = file.getAbsolutePath();
        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Override
    public void delete(Integer id) throws IOException {
        flightTable.remove(id);
        this.save();
    }

    @Override
    public void update(FlightEntity flightEntity, Integer id) throws IOException {
        flightTable.remove(id);
        flightTable.put(id, flightEntity);
        this.save();
    }

    @Override
    public void add(FlightEntity flightEntity) throws IOException {
        Integer id = new Random().nextInt(900) + 100;
        while (flightTable.keySet().contains(id)){
            id = new Random().nextInt(900) + 100;
        }
        flightTable.put(id, flightEntity);
        this.save();
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
        sb.append("]\n}");
        return sb.toString();
    }
}
