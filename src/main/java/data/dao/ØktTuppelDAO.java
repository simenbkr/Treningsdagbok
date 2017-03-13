package data.dao;

import data.db.DB;
import data.mapper.ØktTuppelMapper;
import data.models.ØktTuppel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ØktTuppelDAO implements IDAO<ØktTuppel>{


    public void update(ØktTuppel øktTuppel) {
        //TODONT DO SHIT
    }

    public void delete(ØktTuppel øktTuppel) {
        String sql = "DELETE FROM Økt_har_Øvelse WHERE (Økt_id=? AND Øvelse_id=?)";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql);
            st.setInt(1, øktTuppel.getØkt().getId());
            st.setInt(2, øktTuppel.getØvelse().getId());
            st.executeUpdate();
            kobling.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void create(ØktTuppel øktTuppel) {
        String sql = "INSERT INTO Økt_har_Øvelse (Økt_id,Øvelse_id,Miljø_id,Resultat_id)" +
                "VALUES(?,?,?,?)";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql);
            st.setInt(1, øktTuppel.getØkt().getId());
            st.setInt(2, øktTuppel.getØvelse().getId());
            st.setInt(3, øktTuppel.getMiljø().getId());
            st.setInt(4, øktTuppel.getResultat().getId());
            st.execute();
            kobling.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<ØktTuppel> listAll() {
        List<ØktTuppel> økta = new ArrayList<ØktTuppel>();
        String SQL = "SELECT * FROM Økt_har_Øvelse";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            while(resultSet.next()){
                økta.add(new ØktTuppelMapper().mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return økta;
    }

    public ØktTuppel getByID(int id) {
        return null;
    }

    public List<ØktTuppel> getByØktId(int id){
        List<ØktTuppel> økta = new ArrayList<ØktTuppel>();
        String SQL = "SELECT * FROM Økt_har_Øvelse WHERE Økt_id=" + id;
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            while(resultSet.next()){
                økta.add(new ØktTuppelMapper().mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return økta;
    }

    public List<ØktTuppel> getByØvelseId(int id){
        List<ØktTuppel> økta = new ArrayList<ØktTuppel>();
        String SQL = "SELECT * FROM Økt_har_Øvelse WHERE Øvelse_id=" + id;
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            while(resultSet.next()){
                økta.add(new ØktTuppelMapper().mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return økta;
    }

}
