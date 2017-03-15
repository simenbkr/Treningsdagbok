package data.dao;

import data.db.DB;
import data.mapper.MiljøMapper;
import data.models.Miljø;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MiljøDAO implements IDAO<Miljø> {

    public void update(Miljø miljø) {
        String sql = "UPDATE Miljø SET Inne_Id=?,Ute_Id=? WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            try {
                st.setInt(1, miljø.getInne().getId());
                st.setInt(2, 0);
            } catch (NullPointerException e) {
                st.setInt(1, 0);
                st.setInt(2, miljø.getUte().getId());
            }
            st.setInt(3, miljø.getId());
            st.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Miljø miljø) {
        String sql = "DELETE FROM Miljø WHERE id=?";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, miljø.getId());
            st.executeUpdate();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int create(Miljø miljø) {
        String SQL = "INSERT INTO Miljø (Inne_id, Ute_id) VALUES (?, ?);";
        int lastID = -1;
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            try {
                ps.setInt(1, miljø.getInne().getId());
                ps.setInt(2, 0);
            } catch (NullPointerException e) {
                ps.setInt(1, 0);
                ps.setInt(2, miljø.getUte().getId());
            }
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

    public List<Miljø> listAll() {
        String SQL = "SELECT * FROM Miljø;";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Miljø> miljøList = new ArrayList<Miljø>();
            MiljøMapper mapper = new MiljøMapper();
            while (resultSet.next()) {
                miljøList.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            return miljøList;
        } catch (SQLException e) {
            return null;
        }
    }

    public Miljø getByID(int id) {
        String SQL = "SELECT * FROM Miljø WHERE ID=" + String.valueOf(id) + ";";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst(); resultSet.next();
            Miljø miljø = new MiljøMapper().mapRow(resultSet, 0);
            connection.close();
            return miljø;
        } catch (SQLException e) {
            return null;
        }
    }
}
