package by.epam.ultraair.dao.repositories;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;

public interface DAO<K, T> {

    Optional<T> get(K username);

    Hashtable<K, T> getAll();

    void save() throws IOException;

    void add(T t) throws IOException;

    void update (T t, K id) throws IOException;

    void delete(K id) throws IOException;
}
