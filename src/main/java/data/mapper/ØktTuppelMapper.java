package data.mapper;

import data.models.ØktTuppel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ØktTuppelMapper implements RowMapper<ØktTuppel> {
    public ØktTuppel mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
