package data.mapper;

import data.dao.StyrkeDAO;
import data.dao.UtholdenhetDAO;
import data.models.Resultat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultatMapper implements RowMapper<Resultat> {
    public Resultat mapRow(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.getInt("Styrke_id") == 0) {
            return new Resultat(resultSet.getInt("id"), new UtholdenhetDAO().getByID(resultSet.getInt("Utholdenhet_id")));
        }
        return new Resultat(resultSet.getInt("id"), new StyrkeDAO().getByID(resultSet.getInt("Styrke_id")));

    }
}
