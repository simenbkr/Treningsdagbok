package data.mapper;

import data.models.Øvelse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ØvelseMapper implements RowMapper<Øvelse> {

    public Øvelse mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Øvelse(resultSet.getInt("id"), resultSet.getString("navn"), resultSet.getString("beskrivelse"), resultSet.getString("type"));
    }
}
