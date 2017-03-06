package data.mapper;

import data.dao.InneDAO;
import data.dao.UteDAO;
import data.models.Miljø;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MiljøMapper implements RowMapper<Miljø> {
    public Miljø mapRow(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.getInt("Inne_Id") == 0) {
            return new Miljø(resultSet.getInt("id"), new UteDAO().getByID(resultSet.getInt("Ute_Id")));
        }
        return new Miljø(resultSet.getInt("id"), new InneDAO().getByID(resultSet.getInt("Inne_Id")));
    }
}
