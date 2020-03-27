package dao.repositories;

import dao.entities.ClientEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Hashtable;
import java.util.Optional;

public class ClientRepository implements DAO<String, ClientEntity> {

    Hashtable<String, ClientEntity> clientTable = new Hashtable<String, ClientEntity>();

    Logger logger = (Logger) LogManager.getLogger();

    public ClientRepository() throws IOException, ParseException {
        String path = "src/resources/Clients.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(absolutePath));
        JSONArray jsonArray = (JSONArray) jsonObject.get("clients");

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) jsonParser.parse(o.toString());

            String username = object.get("username").toString();
            boolean isAdmin = Boolean.valueOf(object.get("isAdmin").toString());

            clientTable.put(username, new ClientEntity(username, isAdmin));
        }
    }

    @Override
    public Optional<ClientEntity> get(String username) {
        return Optional.of(clientTable.get(username));
    }

    @Override
    public Hashtable<String, ClientEntity> getAll() {
        return clientTable;
    }

    @Override
    public void save() throws IOException {
        String path = "src/resources/Clients.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        String str = this.toString();
        FileOutputStream outputStream = new FileOutputStream(absolutePath);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    @Override
    public void add(ClientEntity clientEntity) throws IOException {
        clientTable.put(clientEntity.getUsername(), clientEntity);
        this.save();
    }

    @Override
    public void delete(String username) throws IOException {
        clientTable.remove(username);
        this.save();
    }

    @Override
    public void update(ClientEntity clientEntity, String username) throws IOException {
        clientTable.remove(username);
        clientTable.put(username, clientEntity);
        this.save();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\n");
        sb.append("\"clients\" : [\n");
        for (String key: clientTable.keySet()){
            sb.append("{\n");
            sb.append(clientTable.get(key).toString());
            sb.append("},\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]\n}");
        return sb.toString();
    }


}
