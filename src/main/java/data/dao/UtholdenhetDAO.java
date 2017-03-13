package data.dao;

import data.db.DB;
import data.mapper.UtholdenhetMapper;
import data.models.Utholdenhet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtholdenhetDAO implements IDAO<Utholdenhet> {

    public void update(Utholdenhet utholdenhet) {
        String sql = "UPDATE Utholdenhet SET lengde=?, enhet=? WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, utholdenhet.getLengde());
            st.setString(2, utholdenhet.getEnhet());
            st.setInt(3, utholdenhet.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Utholdenhet utholdenhet) {
        String sql = "DELETE FROM Utholdenhet WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, utholdenhet.getId());
            st.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void create(Utholdenhet utholdenhet) {
        String SQL = "INSERT INTO Utholdenhet (lengde, enhet) VALUES (?, ?);";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(SQL);
            ps.setFloat(1, utholdenhet.getLengde());
            ps.setString(2, utholdenhet.getEnhet());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utholdenhet> listAll() {
        String SQL = "SELECT * FROM Utholdenhet;";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Utholdenhet> utholdenhetList = new ArrayList<Utholdenhet>();
            UtholdenhetMapper mapper = new UtholdenhetMapper();
            while (resultSet.next()) {
                utholdenhetList.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            return utholdenhetList;
        } catch (SQLException e) {
            return null;
        }
    }

    public Utholdenhet getByID(int id) {
        String SQL = "SELECT * FROM Utholdenhet WHERE ID=" + String.valueOf(id) + ";";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst(); resultSet.next();
            return new UtholdenhetMapper().mapRow(resultSet, 0);
        } catch (SQLException e) {
            return null;
        }
    }
}
