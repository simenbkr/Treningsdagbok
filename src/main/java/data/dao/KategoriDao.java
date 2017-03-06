package data.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import data.db.DB;
import data.mapper.KategoriMapper;
import data.models.Kategori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KategoriDao implements IDAO<Kategori> {


    public void update(Kategori kategori) {
        String sql = "UPDATE Kategori SET navn=?,foreldre_id=? WHERE id=?";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql);
            st.setString(1, kategori.getNavn());
            st.setInt(2, kategori.getForeldreId());
            st.setInt(3, kategori.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Kategori kategori) {
        String sql = "DELETE FROM Kategori WHERE id=?";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql);
            st.setInt(1, kategori.getId());
            st.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void create(Kategori kategori) {
        String sql = "INSERT INTO Kategori (navn,foreldre_id) VALUES(?,?)";
        try {
            Connection kobling = DB.getConnection();
            PreparedStatement st = kobling.prepareStatement(sql);
            st.setString(1, kategori.getNavn());
            st.setInt(2, kategori.getForeldreId());
            st.execute();
        } catch(MySQLIntegrityConstraintViolationException e){
            try {
                String sqlV2 = "INSERT INTO Kategori (navn) VALUES(?)";
                Connection kobling = DB.getConnection();
                PreparedStatement st = kobling.prepareStatement(sqlV2);
                st.setString(1, kategori.getNavn());
                st.execute();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Kategori> listAll() {
        List<Kategori> kategoriene = new ArrayList<Kategori>();
        try {
            ResultSet radene = DB.getConnection().createStatement().executeQuery("SELECT * FROM Kategori");
            radene.beforeFirst();
            while(radene.next()) {
                kategoriene.add(new KategoriMapper().mapRow(radene,radene.getRow()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategoriene;
    }

    public Kategori getByID(int id) {
        try {
            ResultSet rad = DB.getConnection().createStatement().executeQuery("SELECT * FROM Kategori WHERE id=" + id + ";");
            return new KategoriMapper().mapRow(rad,1);
        } catch (SQLException e) {
            return null;
        }
    }
}