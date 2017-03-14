package data.dao;

import data.db.DB;
import data.mapper.StyrkeMapper;
import data.models.Styrke;

import java.sql.*;
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
            connection.close();
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
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int create(Styrke styrke) {
        String SQL = "INSERT INTO Styrke (belastning, reps, sett) VALUES (?, ?, ?);";
        int lastID = -1;
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, styrke.getBelastning());
            ps.setInt(2, styrke.getReps());
            ps.setDouble(3, styrke.getSett());
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

    public List<Styrke> listAll() {
        String SQL = "SELECT * FROM Styrke;";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Styrke> styrkeList = new ArrayList<Styrke>();
            StyrkeMapper mapper = new StyrkeMapper();
            while (resultSet.next()) {
                styrkeList.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
            return styrkeList;
        } catch (SQLException e) {
            return null;
        }
    }

    public Styrke getByID(int id) {
        String SQL = "SELECT * FROM Styrke WHERE ID=" + String.valueOf(id) + ";";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst(); resultSet.next();
            Styrke styrke = new StyrkeMapper().mapRow(resultSet, 0);
            connection.close();
            return styrke;
        } catch (SQLException e) {
            return null;
        }
    }
}
