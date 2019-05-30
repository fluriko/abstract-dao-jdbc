package mate.academy.dao.helper;

import org.apache.log4j.Logger;
import java.lang.reflect.Field;
import java.util.Arrays;

public class QueryFormer<T, ID> {
    private static final Logger logger = Logger.getLogger(QueryFormer.class);
    private Class<T> clazzT;

    public QueryFormer(Class<T> clazzT) {
        this.clazzT = clazzT;
    }

    public String getQueryForSave(T object) {
        String table = getTableName();
        String columns = "(" + getColumns() + ")";
        String values = "(" + getValues(object) + ")";
        return  "INSERT INTO " + table + columns + " VALUES" + values;
    }

    public String getQueryForGet(ID id) {
        String table = getTableName();
        String columns = getColumns();
        return  "SELECT " + columns + " FROM " + table + " WHERE ID = '" + id + "'";
    }

    public String getQueryForUpdate(T object) {
        String table = getTableName();
        String updateFields = getUpdateFields(object);
        String IdValue = getIdValue(object);
        return "UPDATE " + table + " SET " + updateFields + " WHERE ID = '" + IdValue + "'";
    }

    public String getQueryForDelete(T object) {
        String table = getTableName();
        String IdValue = getIdValue(object);
        return  "DELETE FROM " + table + " WHERE ID = '" + IdValue + "'";
    }

    public String getQueryForGetAll() {
        String table = getTableName();
        String columns = getColumns();
        return  "SELECT " + columns + " FROM " + table;
    }

    private String getTableName() {
        return clazzT.getSimpleName().toUpperCase() + "S";
    }

    private String getColumns() {
        StringBuilder columns = new StringBuilder();
        Field[] fields = clazzT.getDeclaredFields();
        Arrays.stream(fields)
                .peek((field -> field.setAccessible(true)))
                .forEach(field -> columns.append(field.getName()).append(","));
        columns.deleteCharAt(columns.length() - 1);
        return columns.toString().toUpperCase();
    }

    private String getValues(T object) {
        StringBuilder values = new StringBuilder();
        Field[] fields = clazzT.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                values
                        .append("'")
                        .append(field.get(object))
                        .append("',");
            } catch (IllegalAccessException e) {
                logger.error("Error in getting values", e);
            }
        }
        values.deleteCharAt(values.length() - 1);
        return values.toString();
    }

    private String getIdValue(T object) {
        try {
            Field field = clazzT.getDeclaredField("id");
            field.setAccessible(true);
            return field.get(object).toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Error in getting id value", e);
            return "";
        }
    }

    private String getUpdateFields(T object) {
        StringBuilder updateFields = new StringBuilder();
        Field[] fields = clazzT.getDeclaredFields();
        Arrays.stream(fields)
                .peek((field -> field.setAccessible(true)))
                .forEach(field -> {
                    try {
                        updateFields
                                .append(field.getName().toUpperCase())
                                .append(" = '")
                                .append(field.get(object))
                                .append("',");
                    } catch (IllegalAccessException e) {
                        logger.error("Error in getting update fields", e);
                    }
                });
        updateFields.deleteCharAt(updateFields.length() - 1);
        return updateFields.toString();
    }
}
