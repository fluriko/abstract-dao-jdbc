package mate.academy.dao;

import mate.academy.dao.helper.QueryFormer;
import org.apache.log4j.Logger;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    private final Class<T> clazzT;
    private final QueryFormer<T, ID> queryFormer;
    protected final Connection connection;

    public AbstractDao(Class<T> clazzT) {
        this.clazzT = clazzT;
        queryFormer = new QueryFormer<>(clazzT);
        this.connection = DatabaseConnector.connect().get();
    }

    public T save(T object) {
        String query = queryFormer.getQueryForSave(object);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Exception in saving to h2", e);
        }
        return object;
    }

    public Optional<T> get(ID id) {
        String query = queryFormer.getQueryForGet(id);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Exception in getting from h2", e);
        }
        return Optional.empty();
    }

    private Optional<T> getFromResultSet(ResultSet resultSet) {
        try {
            T object = clazzT.newInstance();
            Field[] fields = clazzT.getDeclaredFields();
            Arrays.stream(fields)
                    .peek((field -> field.setAccessible(true)))
                    .forEach((field -> {
                        try {
                            field.set(object, resultSet.getObject(field.getName().toUpperCase()));
                        } catch (IllegalAccessException | SQLException e) {
                            logger.error("Exception set field in getFromResultSet", e);
                        }
                    }));
            return Optional.ofNullable(object);
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Exception in getting from Result Set", e);
            return Optional.empty();
        }
    }

    public T update(T object) {
        String query = queryFormer.getQueryForUpdate(object);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Exception in updating to h2", e);
        }
        return object;
    }

    public T delete(T object) {
        String query = queryFormer.getQueryForDelete(object);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Exception in deleting from h2", e);
        }
        return object;
    }

    public Optional<List<T>> getAll() {
        String query = queryFormer.getQueryForGetAll();
        List<T> objects = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                T object = getFromResultSet(resultSet).get();
                objects.add(object);
            }
        } catch (SQLException e) {
            logger.error("Error in getting all", e);
        }
        return Optional.ofNullable(objects);
    }
}
