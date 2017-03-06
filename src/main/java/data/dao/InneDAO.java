package data.dao;

import data.db.DB;
import data.mapper.InneMapper;
import data.models.Inne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InneDAO implements IDAO<Inne> {

    public void update(Inne inne) {
        String sql = "UPDATE Inne SET luft=?,tilskuere=? WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, inne.getLuft());
            st.setInt(2, inne.getTilskuere());
            st.setInt(3, inne.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Inne inne) {
        String sql = "DELETE FROM Inne WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, inne.getId());
            st.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void create(Inne inne) {
        String SQL = "INSERT INTO Inne (luft, tilskurere) VALUES (?, ?);";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(SQL);
            ps.setString(1, inne.getLuft());
            ps.setInt(2, inne.getTilskuere());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Inne> listAll() {
        String SQL = "SELECT * FROM Inne;";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Inne> inneList = new ArrayList<Inne>();
            InneMapper mapper = new InneMapper();
            while (resultSet.next()) {
                inneList.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            return inneList;
        } catch (SQLException e) {
            return null;
        }
    }

    public Inne getByID(int id) {
        String SQL = "SELECT * FROM Inne WHERE ID=" + String.valueOf(id) + ";";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            return new InneMapper().mapRow(resultSet, 0);
        } catch (SQLException e) {
            return null;
        }
    }
}
