package DAO;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(Integer id);

    Hashtable<Integer, T> getAll();

    void save() throws IOException;

    void add(T t) throws IOException;

    void update (T t, Integer id);

    void delete(T t, Integer id);
}
