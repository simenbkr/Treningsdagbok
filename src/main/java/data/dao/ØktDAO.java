package data.dao;


import data.db.DB;
import data.mapper.PulsMapper;
import data.mapper.ØktMapper;
import data.models.Puls;
import data.models.Økt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void create(Økt økt) {
        String sql = "INSERT INTO Økt (varighet, tidspunkt, form, prestasjon, notat) " +
                "VALUES(?,?,?,?,?)";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql);
            st.setInt(1, økt.getVarighet());
            st.setTimestamp(2, økt.getTidspunkt());
            st.setString(3, økt.getForm());
            st.setString(4, økt.getPrestasjon());
            st.setString(5, økt.getNotat());
            st.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Økt> listAll() {
        List<Økt> øktene = new ArrayList<Økt>();
        try {
            ResultSet radene = DB.getConnection().createStatement().executeQuery("SELECT * FROM Økt");
            radene.beforeFirst();
            while(radene.next()){
                øktene.add(new ØktMapper().mapRow(radene, radene.getRow()));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return øktene;
    }

    public Økt getByID(int id) {
        try {
            ResultSet rad = DB.getConnection().createStatement().executeQuery("SELECT * FROM Økt WHERE id=" + id);
            return new ØktMapper().mapRow(rad,1337);
        } catch(SQLException e){
            return null;
        }
    }

    public List<Puls> getPulses(int id) {
        List<Puls> pulses = new ArrayList<>();
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery("SELECT  * FROM Puls WHERE Økt_id=" + id);
            resultSet.beforeFirst();
            while (resultSet.next()) {
                pulses.add(new PulsMapper().mapRow(resultSet,1));
            }
            return pulses;
        } catch (SQLException sqle) {
            return null;
        }
    }
}
