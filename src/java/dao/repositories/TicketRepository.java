package dao.repositories;

import dao.entities.TicketEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Random;

public class TicketRepository implements DAO<Integer, TicketEntity> {

    Hashtable<Integer, TicketEntity> ticketTable = new Hashtable<Integer, TicketEntity>();

    public TicketRepository() throws IOException, ParseException {
        String path = "src/resources/Tickets.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(absolutePath));
        JSONArray jsonArray = (JSONArray) jsonObject.get("tickets");

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) jsonParser.parse(o.toString());

            Integer id = Integer.valueOf(object.get("id").toString());

            int flightId = Integer.valueOf(object.get("flightId").toString());
            double price = Double.valueOf(object.get("price").toString());
            boolean rightFirstSitting = Boolean.valueOf(object.get("rightFirstSitting").toString());
            boolean rightFirstRegistration = Boolean.valueOf(object.get("rightFirstRegistration").toString());
            String username = object.get("username").toString();

            ticketTable.put(id, new TicketEntity(flightId, price, rightFirstSitting, rightFirstRegistration, username));
        }
    }

    @Override
    public Optional<TicketEntity> get(Integer id) {
        return Optional.of(ticketTable.get(id));
    }

    @Override
    public Hashtable<Integer, TicketEntity> getAll() {
        return ticketTable;
    }

    @Override
    public void save() throws IOException {
        String path = "src/resources/Tickets.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(absolutePath);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Override
    public void add(TicketEntity ticketEntity) throws IOException {
        Integer id = new Random().nextInt(9000) + 1000;
        while (ticketTable.keySet().contains(id)){
            id = new Random().nextInt(9000) + 1000;
        }
        ticketTable.put(id, ticketEntity);
        this.save();
    }

    @Override
    public void update(TicketEntity ticketEntity, Integer id) throws IOException {
        ticketTable.remove(id);
        ticketTable.put(id, ticketEntity);
        this.save();
    }

    @Override
    public void delete(Integer id) {
        ticketTable.remove(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        sb.append("\"tickets\" : [\n");
        for (Integer key: ticketTable.keySet()){
            sb.append("{\n");
            sb.append("\"id\" : ").append(key).append(",\n");
            sb.append(ticketTable.get(key).toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n}");
        return sb.toString();
    }
}
