package data.dao;

import data.db.DB;
import data.mapper.PulsMapper;
import data.models.Puls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PulsDAO implements IDAO<Puls> {
    public void update(Puls puls) {
        String SQL = "UPDATE Puls SET tid=?, puls=?, lengde=?, høyde=?, bredde=?, Økt_id=? WHERE ID=" + String.valueOf(puls.getId()) + ";";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setTimestamp(1, puls.getTid());
            ps.setInt(2, puls.getPuls());
            ps.setDouble(3, puls.getLengde());
            ps.setDouble(4, puls.getHøyde());
            ps.setDouble(5, puls.getBredde());
            ps.setInt(6, puls.getØktId());
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Puls puls) {
        String SQL = "DELETE * FROM Puls WHERE ID=" + String.valueOf(puls.getId()) + ";";
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

    public void create(Puls puls) {
        String SQL = "INSERT INTO Puls (tid, puls, lengde, høyde, bredde, Økt_id) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            Connection connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setTimestamp(1, puls.getTid());
            ps.setInt(2, puls.getPuls());
            ps.setDouble(3, puls.getLengde());
            ps.setDouble(4, puls.getHøyde());
            ps.setDouble(5, puls.getBredde());
            ps.setInt(6, puls.getØktId());
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Puls> listAll() {
        String SQL = "SELECT * FROM Puls;";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Puls> pulses = new ArrayList<Puls>();
            PulsMapper mapper = new PulsMapper();
            while (resultSet.next()) {
                pulses.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            connection.close();
            return pulses;
        } catch (SQLException e) {
            return null;
        }
    }

    public Puls getByID(int id) {
        String SQL = "SELECT * FROM Puls WHERE ID=" + String.valueOf(id) + ";";
        try {
            Connection connection = DB.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            resultSet.next();
            Puls puls = new PulsMapper().mapRow(resultSet, 0);
            connection.close();
            return puls;
        } catch (SQLException e) {
            return null;
        }
    }

}

