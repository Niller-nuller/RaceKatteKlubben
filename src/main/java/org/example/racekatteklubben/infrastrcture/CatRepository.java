package org.example.racekatteklubben.infrastrcture;

import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.entity.interfaces.ICatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
@Repository
public class CatRepository implements ICatRepository {

    private final JdbcTemplate jdbcTemplate;

    public CatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cat> requestFullCatList() {
        String sql = "SELECT * FROM cats";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Cat(
                        rs.getLong("id"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getInt("age"),
                        rs.getTimestamp("dateOfBirth").toLocalDateTime(),
                        rs.getBoolean("isDead"),
                        rs.getTimestamp("dateOfDeath").toLocalDateTime(),
                        rs.getString("furColorCode"),
                        rs.getString("patternCode"),
                        rs.getString("breedCode"),
                        rs.getString("eyeCode"),
                        rs.getString("fullCode"),
                        rs.getLong("ownerId"),
                        rs.getLong("breederId"),
                        rs.getLong("fatherId"),
                        rs.getLong("motherId")
                )
        );
    }

    @Override
    public void createCat(Cat cat) {
        System.out.println("Kat oprettet: " + cat.getName());
    }
}
