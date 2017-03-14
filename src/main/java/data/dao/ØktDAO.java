package data.dao;


import data.db.DB;
import data.mapper.PulsMapper;
import data.mapper.ØktMapper;
import data.models.Puls;
import data.models.Økt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ØktDAO implements IDAO<Økt> {

    public void update(Økt økt) {
        String sql = "UPDATE Økt SET varighet=?,tidspunkt=?,form=?,prestasjon=?,notat=? WHERE id=?";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareCall(sql);
            st.setInt(1,økt.getVarighet());
            st.setTimestamp(2, økt.getTidspunkt());
            st.setString(3, økt.getForm());
            st.setString(4, økt.getPrestasjon());
            st.setString(5, økt.getNotat());
            st.setInt(6, økt.getId());
            st.executeUpdate();
            kobling.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(Økt økt) {
        String sql = "DELETE FROM Økt WHERE id=?";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql);
            st.setInt(1,økt.getId());
            st.executeUpdate();
            kobling.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int create(Økt økt) {
        String sql = "INSERT INTO Økt (varighet, tidspunkt, form, prestasjon, notat) " +
                "VALUES(?,?,?,?,?)";
        int lastID = -1;
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, økt.getVarighet());
            st.setTimestamp(2, økt.getTidspunkt());
            st.setString(3, økt.getForm());
            st.setString(4, økt.getPrestasjon());
            st.setString(5, økt.getNotat());
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                lastID = rs.getInt(1);
            }
            kobling.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return lastID;
    }

    public List<Økt> listAll() {
        List<Økt> øktene = new ArrayList<Økt>();
        try {
            Connection connection = DB.getConnection();
            ResultSet radene = connection.createStatement().executeQuery("SELECT * FROM Økt");
            radene.beforeFirst();
            while(radene.next()){
                øktene.add(new ØktMapper().mapRow(radene, radene.getRow()));
            }
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return øktene;
    }

    public Økt getByID(int id) {
        try {
            String SQL = "SELECT * FROM Økt WHERE id=" + id;
            Connection connection = DB.getConnection();
            ResultSet rad = connection.createStatement().executeQuery(SQL);
            rad.beforeFirst(); rad.next();
            Økt økt = new ØktMapper().mapRow(rad,rad.getRow());
            connection.close();
            return økt;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Puls> getPulses(int id) {
        List<Puls> pulses = new ArrayList<>();
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT  * FROM Puls WHERE Økt_id=" + id);
            resultSet.beforeFirst();
            while (resultSet.next()) {
                pulses.add(new PulsMapper().mapRow(resultSet,1));
            }
            connection.close();
            return pulses;
        } catch (SQLException sqle) {
            return null;
        }
    }
}
