package data.mapper;

import data.models.Inne;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InneMapper implements RowMapper<Inne>{

    public Inne mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Inne(resultSet.getInt("id"), resultSet.getString("luft"), resultSet.getInt("tilskuere"));
    }
}