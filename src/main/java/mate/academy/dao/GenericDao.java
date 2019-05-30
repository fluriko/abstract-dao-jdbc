package mate.academy.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {
    T save(T object);
    Optional<T> get(ID id);
    T update(T object);
    T delete(T object);
    Optional<List<T>> getAll();
}
