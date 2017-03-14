package data.dao;

import data.db.DB;
import data.mapper.ResultatMapper;
import data.models.Resultat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultatDAO implements IDAO<Resultat> {
    public void update(Resultat resultat) {
        String SQL = "UPDATE Resultat SET Styrke_id=?, Utholdenhet_id=? WHERE ID=" + String.valueOf(resultat.getId()) + ";";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, resultat.getStyrke() != null ? resultat.getStyrke().getId() : 0);
            ps.setInt(2, resultat.getUtholdenhet() != null ? resultat.getUtholdenhet().getId() : 0);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Resultat resultat) {
        String SQL = "DELETE * FROM Resultat WHERE ID=" + String.valueOf(resultat.getId()) + ";";
        Connection connection = DB.getConnection();
        try {
            connection.createStatement().executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Resultat resultat) {
        String SQL = "INSERT INTO Resultat (Styrke_id, Utholdenhet_id) VALUES (?, ?);";
        int lastID = -1;
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, resultat.getStyrke() != null ? resultat.getStyrke().getId() : 0);
            ps.setInt(2, resultat.getUtholdenhet() != null ? resultat.getUtholdenhet().getId() : 0);
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

    public List<Resultat> listAll() {
        String SQL = "SELECT * FROM Resultat;";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Resultat> resultats = new ArrayList<Resultat>();
            ResultatMapper mapper = new ResultatMapper();
            while (resultSet.next()) {
                resultats.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
            return resultats;
        } catch (SQLException e) {
            return null;
        }
    }

    public Resultat getByID(int id) {
        String SQL = "SELECT * FROM Resultat WHERE ID=" + String.valueOf(id) + ";";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            resultSet.next();
            Resultat resultat = new ResultatMapper().mapRow(resultSet, 0);
            connection.close();
            return resultat;
        } catch (SQLException e) {
            return null;
        }
    }
}
