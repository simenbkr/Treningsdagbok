package data.mapper;

import data.dao.MiljøDAO;
import data.dao.ResultatDAO;
import data.dao.ØktDAO;
import data.dao.ØvelseDAO;
import data.models.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ØktTuppelMapper implements RowMapper<ØktTuppel> {
    public ØktTuppel mapRow(ResultSet resultSet, int i) throws SQLException {
        Økt økt = new ØktDAO().getByID(resultSet.getInt("Økt_id"));
        Øvelse øvelse = new ØvelseDAO().getByID(resultSet.getInt("Øvelse_id"));
        Miljø miljø = new MiljøDAO().getByID(resultSet.getInt("Miljø_id"));
        Resultat resultat = new ResultatDAO().getByID(resultSet.getInt("Resultat_id"));
        return new ØktTuppel(økt, øvelse, miljø, resultat);
    }
}
