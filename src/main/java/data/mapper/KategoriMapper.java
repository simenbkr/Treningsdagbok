package data.mapper;

import data.models.Kategori;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KategoriMapper implements RowMapper<Kategori> {


    public Kategori mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Kategori(resultSet.getInt("id"), resultSet.getString("navn"), resultSet.getInt("foreldre_id"));
    }
}
