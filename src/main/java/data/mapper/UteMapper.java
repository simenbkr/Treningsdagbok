
package data.mapper;
import data.models.Ute;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UteMapper implements RowMapper<Ute>{

    public Ute mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Ute(resultSet.getInt("id"), resultSet.getString("værforhold"), resultSet.getString("værtype"), resultSet.getFloat("temperatur"));
    }
}