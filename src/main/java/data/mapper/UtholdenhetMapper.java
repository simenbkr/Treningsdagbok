package data.mapper;

import data.models.Utholdenhet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtholdenhetMapper implements RowMapper<Utholdenhet> {
    public Utholdenhet mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Utholdenhet(resultSet.getInt("id"), resultSet.getFloat("lengde"), resultSet.getString("enhet"));
    }
}
