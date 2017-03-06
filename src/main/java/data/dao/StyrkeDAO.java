package data.dao;

import data.db.DB;
import data.mapper.StyrkeMapper;
import data.models.Styrke;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StyrkeDAO implements IDAO<Styrke> {
    
    public void update(Styrke styrke) {
        String sql = "UPDATE Styrke SET belastning=?,sett=?,reps=? WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, styrke.getBelastning());
            st.setInt(2, styrke.getSett());
            st.setInt(3, styrke.getReps());
            st.setInt(4, styrke.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Styrke styrke) {
        String sql = "DELETE FROM Styrke WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, styrke.getId());
            st.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void create(Styrke styrke) {
        String SQL = "INSERT INTO Styrke (belastning, reps, sett) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(SQL);
            ps.setFloat(1, styrke.getBelastning());
            ps.setInt(2, styrke.getReps());
            ps.setDouble(3, styrke.getSett());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Styrke> listAll() {
        String SQL = "SELECT * FROM Styrke;";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Styrke> styrkeList = new ArrayList<Styrke>();
            StyrkeMapper mapper = new StyrkeMapper();
            while (resultSet.next()) {
                styrkeList.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            return styrkeList;
        } catch (SQLException e) {
            return null;
        }
    }

    public Styrke getByID(int id) {
        String SQL = "SELECT * FROM Styrke WHERE ID=" + String.valueOf(id) + ";";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            return new StyrkeMapper().mapRow(resultSet, 0);
        } catch (SQLException e) {
            return null;
        }
    }
}
