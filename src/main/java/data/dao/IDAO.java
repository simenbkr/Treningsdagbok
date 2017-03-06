package data.dao;

import java.util.List;

public interface IDAO<T> {

    void update(T t);
    void delete(T t);
    void create(T t);

    List<T> listAll();
    T getByID(T t);

}
