package service;

import dao.entities.FlightEntity;
import dao.repositories.FlightRepository;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class FlightIndexIdMapper {
    private ArrayList<Integer> indexToIdMap = new ArrayList<>();

    public FlightIndexIdMapper() throws ParseException, java.text.ParseException, IOException {
        Hashtable<Integer, FlightEntity>  hashtable = new FlightRepository().getAll();
        indexToIdMap.addAll(hashtable.keySet());
    }

    public Integer getIdByIndex(int index){
        return indexToIdMap.get(index);
    }
}
