package data.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import data.db.DB;
import data.mapper.KategoriMapper;
import data.mapper.ØvelseMapper;
import data.models.Kategori;
import data.models.Øvelse;

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
            kobling.close();
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
            kobling.close();
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
            kobling.close();
        } catch(MySQLIntegrityConstraintViolationException e){
            try {
                String sqlV2 = "INSERT INTO Kategori (navn) VALUES(?)";
                Connection kobling = DB.getConnection();
                PreparedStatement st = kobling.prepareStatement(sqlV2);
                st.setString(1, kategori.getNavn());
                st.execute();
                kobling.close();
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
            Connection connection = DB.getConnection();
            ResultSet radene = connection.createStatement().executeQuery("SELECT * FROM Kategori");
            radene.beforeFirst();
            while(radene.next()) {
                kategoriene.add(new KategoriMapper().mapRow(radene,radene.getRow()));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategoriene;
    }

    public Kategori getByID(int id) {
        try {
            Connection connection = DB.getConnection();
            ResultSet rad = connection.createStatement().executeQuery("SELECT * FROM Kategori WHERE id=" + id + ";");
            rad.beforeFirst(); rad.next();

            Kategori kategori = new KategoriMapper().mapRow(rad,1);
            connection.close();
            return kategori;
        } catch (SQLException e) {
            return null;
        }
    }

    public Kategori getParentCategory(Kategori kategori) {
        if (kategori.getForeldreId() == 0) return null;
        return getByID(kategori.getForeldreId());
    }

    public List<Kategori> getSubCategories(Kategori kategori) {
        String SQL = "SELECT * FROM Kategori WHERE foreldre_id=" + String.valueOf(kategori.getId()) + ";";
        List<Kategori> categories = new ArrayList<Kategori>();
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            KategoriMapper mapper = new KategoriMapper();
            while (resultSet.next()) {
                categories.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Øvelse> getØvelserInCategory(Kategori kategori){
        List<Øvelse> øvelses = new ArrayList<Øvelse>();
        String SQL = "SELECT Øvelses_id FROM Øvelse_tilhører_Kategori WHERE Kategori_id=" + String.valueOf(kategori.getId()) + ";";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            ØvelseDAO øvelseDAO = new ØvelseDAO();
            while (resultSet.next()) {
                øvelses.add(øvelseDAO.getByID(resultSet.getInt("Øvelses_id")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return øvelses;
    }
}