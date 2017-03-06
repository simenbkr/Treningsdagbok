package data.mapper;

import data.models.Økt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ØktMapper implements RowMapper<Økt> {
    public Økt mapRow(ResultSet resultSet, int i) throws SQLException {
        Økt økt = new Økt(resultSet.getInt("id"), resultSet.getTimestamp("tidspunkt"), resultSet.getString("form"), resultSet.getString("notat"));
        økt.setPrestasjon(resultSet.getString("prestasjon"));
        økt.setVarighet(resultSet.getInt("varighet"));
        return økt;
    }
}
