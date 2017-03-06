package data.dao;

import data.db.DB;
import data.mapper.ØvelseMapper;
import data.models.Kategori;
import data.models.Øvelse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ØvelseDAO implements IDAO<Øvelse> {
    public void update(Øvelse øvelse) {
        String SQL = "UPDATE Øvelse SET navn=?, beskrivelse=?, type=? WHERE ID=" + String.valueOf(øvelse.getId()) + ";";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(SQL);
            ps.setString(1, øvelse.getNavn());
            ps.setString(2, øvelse.getBeskrivelse());
            ps.setString(3, øvelse.getType());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Øvelse øvelse) {
        String SQL = "DELETE * FROM Øvelse WHERE ID=" + String.valueOf(øvelse.getId()) + ";";
        try {
            DB.getConnection().createStatement().execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Øvelse øvelse) {
        String SQL = "INSERT INTO Øvelse (navn, beskrivelse, type) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(SQL);
            ps.setString(1, øvelse.getNavn());
            ps.setString(2, øvelse.getBeskrivelse());
            ps.setString(3, øvelse.getType());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Øvelse> listAll() {
        String SQL = "SELECT * FROM Øvelse;";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Øvelse> øvelses = new ArrayList<Øvelse>();
            ØvelseMapper mapper = new ØvelseMapper();
            while (resultSet.next()) {
                øvelses.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            return øvelses;
        } catch (SQLException e) {
            return null;
        }
    }

    public Øvelse getByID(int id) {
        String SQL = "SELECT * FROM Øvelse WHERE ID=" + String.valueOf(id) + ";";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            return new ØvelseMapper().mapRow(resultSet, 0);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Øvelse> getAlternativeØvelser(Øvelse øvelse) throws SQLException {
        List<Øvelse> øvelses = new ArrayList<Øvelse>();

        String SQL = "SELECT alternative_id FROM Øvelse_har_alternative WHERE Øvelse_id=" + String.valueOf(øvelse.getId()) + ";";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            while (resultSet.next()){
                øvelses.add(getByID(resultSet.getInt("alternative_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return øvelses;
    }

    public List<Kategori> getCategoriesØvelse(Øvelse øvelse){
        List<Kategori> categories = new ArrayList<Kategori>();
        String SQL = "SELECT Kategori_id FROM Øvelse_tilhører_Kategori WHERE Øvelses_id=" + String.valueOf(øvelse.getId()) + ";";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            KategoriDao kategoriDao = new KategoriDao();
            while (resultSet.next()){
                categories.add(kategoriDao.getByID(resultSet.getInt("Kategori_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
