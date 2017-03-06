package data.mapper;

import data.models.Styrke;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StyrkeMapper implements RowMapper<Styrke> {
    public Styrke mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Styrke(resultSet.getInt("id"), resultSet.getFloat("belastning"), resultSet.getInt("reps"), resultSet.getInt("sett"));
    }
}
