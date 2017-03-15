package data.dao;

import data.db.DB;
import data.mapper.UtholdenhetMapper;
import data.models.Utholdenhet;

import java.sql.*;
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
            connection.close();
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
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int create(Utholdenhet utholdenhet) {
        String SQL = "INSERT INTO Utholdenhet (lengde, enhet) VALUES (?, ?);";
        int lastID = -1;
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, utholdenhet.getLengde());
            ps.setString(2, utholdenhet.getEnhet());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                lastID = rs.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastID;
    }

    public List<Utholdenhet> listAll() {
        String SQL = "SELECT * FROM Utholdenhet;";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Utholdenhet> utholdenhetList = new ArrayList<Utholdenhet>();
            UtholdenhetMapper mapper = new UtholdenhetMapper();
            while (resultSet.next()) {
                utholdenhetList.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
            return utholdenhetList;
        } catch (SQLException e) {
            return null;
        }
    }

    public Utholdenhet getByID(int id) {
        String SQL = "SELECT * FROM Utholdenhet WHERE ID=" + String.valueOf(id) + ";";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst(); resultSet.next();
            Utholdenhet utholdenhet = new UtholdenhetMapper().mapRow(resultSet, 0);
            connection.close();
            return utholdenhet;
        } catch (SQLException e) {
            return null;
        }
    }
}
