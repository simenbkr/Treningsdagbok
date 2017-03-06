package data.dao;

import data.db.DB;
import data.mapper.PulsMapper;
import data.models.Puls;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PulsDAO implements IDAO<Puls> {
    public void update(Puls puls) {
        String SQL = "UPDATE Puls SET tid=?, puls=?, lengde=?, høyde=?, bredde=?, Økt_id=? WHERE ID=" + String.valueOf(puls.getId()) + ";";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(SQL);
            ps.setTimestamp(1, puls.getTid());
            ps.setInt(2, puls.getPuls());
            ps.setDouble(3, puls.getLengde());
            ps.setDouble(4, puls.getHøyde());
            ps.setDouble(5, puls.getBredde());
            ps.setInt(6, puls.getØktId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Puls puls) {
        String SQL = "DELETE * FROM Puls WHERE ID=" + String.valueOf(puls.getId()) + ";";
        try {
            DB.getConnection().createStatement().execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Puls puls) {
        String SQL = "INSERT INTO Puls (tid, puls, lengde, høyde, bredde, Økt_id) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(SQL);
            ps.setTimestamp(1, puls.getTid());
            ps.setInt(2, puls.getPuls());
            ps.setDouble(3, puls.getLengde());
            ps.setDouble(4, puls.getHøyde());
            ps.setDouble(5, puls.getBredde());
            ps.setInt(6, puls.getØktId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Puls> listAll() {
        String SQL = "SELECT * FROM Puls;";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            resultSet.beforeFirst();
            List<Puls> pulses = new ArrayList<Puls>();
            PulsMapper mapper = new PulsMapper();
            while (resultSet.next()) {
                pulses.add(mapper.mapRow(resultSet, resultSet.getRow()));
            }
            return pulses;
        } catch (SQLException e) {
            return null;
        }
    }

    public Puls getByID(int id) {
        String SQL = "SELECT * FROM Puls WHERE ID=" + String.valueOf(id) + ";";
        try {
            ResultSet resultSet = DB.getConnection().createStatement().executeQuery(SQL);
            return new PulsMapper().mapRow(resultSet, 0);
        } catch (SQLException e) {
            return null;
        }
    }

}

