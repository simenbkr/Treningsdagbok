package data.mapper;

import data.models.Puls;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PulsMapper implements RowMapper<Puls> {
    public Puls mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Puls(resultSet.getInt("id"), resultSet.getTimestamp("tid"), resultSet.getInt("puls"),
                resultSet.getDouble("lengde"), resultSet.getDouble("høyde"), resultSet.getDouble("bredde"), resultSet.getInt("Økt_id"));
    }
}
