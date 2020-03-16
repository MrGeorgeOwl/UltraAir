package DAO;

import java.util.Hashtable;
import java.util.Optional;

public interface DAO<T> {

    Optional<Flight> get(Integer id);

    Hashtable<Integer, T> getAll();

//    void save(T t);
//
//    void update (T t, String[] params);
//
//    void delete(T t);
}
