package data.dao;

import data.db.DB;
import data.mapper.UteMapper;
import data.models.Ute;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UteDAO implements IDAO<Ute> {

    public void update(Ute ute) {
        String sql = "UPDATE Ute SET værforhold=?,værtype=?,temperatur=? WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ute.getVærforhold());
            st.setString(2, ute.getVærtype());
            st.setFloat(2, ute.getTemperatur());
            st.setInt(3, ute.getId());
            st.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Ute ute) {
        String sql = "DELETE FROM Ute WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ute.getId());
            st.executeUpdate();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int create(Ute ute) {
        String SQL = "INSERT INTO Ute (værforhold, værtype, temperatur) VALUES (?, ?, ?);";
        int lastID = -1;
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ute.getVærforhold());
            ps.setString(2, ute.getVærtype());
            ps.setFloat(3, ute.getTemperatur());
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

    public List<Ute> listAll() {
        String SQL = "SELECT * FROM Ute;";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Ute> uteList = new ArrayList<Ute>();
            UteMapper mapper = new UteMapper();
            while (resultSet.next()) {
                uteList.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
            return uteList;
        } catch (SQLException e) {
            return null;
        }
    }

    public Ute getByID(int id) {
        String SQL = "SELECT * FROM Ute WHERE ID=" + String.valueOf(id) + ";";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst(); resultSet.next();
            Ute ute = new UteMapper().mapRow(resultSet, 0);
            connection.close();
            return ute;
        } catch (SQLException e) {
            return null;
        }
    }
}
