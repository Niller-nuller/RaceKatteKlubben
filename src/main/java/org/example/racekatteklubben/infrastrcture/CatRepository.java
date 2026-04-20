package org.example.racekatteklubben.infrastrcture;

import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.entity.interfaces.ICatRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class CatRepository implements ICatRepository {

    private final JdbcTemplate jdbcTemplate;

    public CatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Cat> requestCatListPopulate(){
        String sql = "SELECT * FROM cats ORDER BY Name ASC";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Cat(
                        rs.getLong("CatId"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getInt("age"),
                        rs.getTimestamp("dateOfBirth") != null ? rs.getDate("dateOfBirth").toLocalDate(): null,
                        rs.getBoolean("isDead"),
                        rs.getTimestamp("dateOfDeath") != null ? rs.getDate("dateOfDeath").toLocalDate(): null,
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

    private List<Cat> createCatList(String sql, long id){
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Cat(
                        rs.getLong("CatId"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getInt("age"),
                        rs.getTimestamp("dateOfBirth") != null ? rs.getDate("dateOfBirth").toLocalDate(): null,
                        rs.getBoolean("isDead"),
                        rs.getTimestamp("dateOfDeath") != null ? rs.getDate("dateOfDeath").toLocalDate(): null,
                        rs.getString("furColorCode"),
                        rs.getString("patternCode"),
                        rs.getString("breedCode"),
                        rs.getString("eyeCode"),
                        rs.getString("fullCode"),
                        rs.getLong("ownerId"),
                        rs.getLong("breederId"),
                        rs.getLong("fatherId"),
                        rs.getLong("motherId")
                ),id
        );
        //I mapper rs.getLong("id"), men i schema hedder kolonnen CatId
        //dateOfDeath kan være null, men I kalder direkte .toLocalDateTime()
        //i databasen står køn som Male og Female, mens jeres enum er MALE og FEMALE
        //DATA.SQL indeholder en linje med spring.sql.init.mode=always, som ikke hører hjemme i en SQL-fil
        //sidste insert i DATA.SQL ser ødelagt ud
        //Pointe: clean architecture handler ikke kun om mapper og lag. Det handler også om tydelige kontrakter mellem lagene. Når databasen siger én ting og Java-koden forventer noget andet, bliver koden skrøbelig.
    }


    private List<Cat> createCatListWithCriteria(String sql, String safeCriteria){
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Cat(
                        rs.getLong("CatId"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getInt("age"),
                        rs.getTimestamp("dateOfBirth") != null ? rs.getDate("dateOfBirth").toLocalDate(): null,
                        rs.getBoolean("isDead"),
                        rs.getTimestamp("dateOfDeath") != null ? rs.getDate("dateOfDeath").toLocalDate(): null,
                        rs.getString("furColorCode"),
                        rs.getString("patternCode"),
                        rs.getString("breedCode"),
                        rs.getString("eyeCode"),
                        rs.getString("fullCode"),
                        rs.getLong("ownerId"),
                        rs.getLong("breederId"),
                        rs.getLong("fatherId"),
                        rs.getLong("motherId")
                ),safeCriteria
        );
    }

    @Override
    public List<Cat> requestFullFilteredCatList(String criteria){
        String sql = "SELECT * FROM cats WHERE name LIKE ? ";
        String safeCriteria = "%" + criteria + "%";
        return createCatListWithCriteria(sql, safeCriteria);
    }

    @Override
    public void createCat(Cat cat) {
        String sql = "INSERT INTO cats (Name, Gender, Age, DateOfBirth, IsDead, DateOfDeath, " +
                "FurColorCode, PatternCode, BreedCode, EyeCode, FullCode, " +
                "OwnerId, BreederId, FatherId, MotherId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                cat.getName(),
                cat.getGender() != null ? cat.getGender().name() : null,
                cat.getAge(),
                cat.getDateOfBirth(),
                cat.isDead(),
                cat.getDateOfDeath(),
                cat.getFurColorCode(),
                cat.getPatternCode(),
                cat.getBreedCode(),
                cat.getEyeCode(),
                cat.getFullCode(),
                cat.getOwnerId(),
                cat.getBreedId(),
                cat.getFatherId(),
                cat.getMotherId());
    }

    @Override
    public List<Cat> createListOfCatsById(long id) {
        String sql = "SELECT * FROM Cats WHERE OwnerId = ?";
        return createCatList(sql,id);
    }

    @Override
    public Cat findById(long id) {
        String sql = "SELECT * FROM cats WHERE CatId = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Cat(
                        rs.getLong("CatId"),
                        rs.getString("name"),
                        Gender.valueOf(rs.getString("gender")),
                        rs.getInt("age"),
                        rs.getTimestamp("dateOfBirth") != null ? rs.getDate("dateOfBirth").toLocalDate(): null,
                        rs.getBoolean("isDead"),
                        rs.getTimestamp("dateOfDeath") != null ? rs.getDate("dateOfDeath").toLocalDate(): null,
                        rs.getString("furColorCode"),
                        rs.getString("patternCode"),
                        rs.getString("breedCode"),
                        rs.getString("eyeCode"),
                        rs.getString("fullCode"),
                        rs.getLong("ownerId"),
                        rs.getLong("breederId"),
                        rs.getLong("fatherId"),
                        rs.getLong("motherId")
                ), id);
    }

    @Override
    public void updateCat(Cat cat) {
        String sql = "UPDATE Cats SET Name = ?, Gender = ?, Age = ?, DateOfBirth = ?, " +
                "IsDead = ?, DateOfDeath = ?, FurColorCode = ?, PatternCode = ?, " +
                "BreedCode = ?, EyeCode = ?, FullCode = ?, " +
                "BreederId = ?, FatherId = ?, MotherId = ? " +
                "WHERE CatId = ?";

        jdbcTemplate.update(sql,
                cat.getName(),
                cat.getGender() != null ? cat.getGender().name() : null,
                cat.getAge(),
                cat.getDateOfBirth(),
                cat.isDead(),
                cat.getDateOfDeath(),
                cat.getFurColorCode(),
                cat.getPatternCode(),
                cat.getBreedCode(),
                cat.getEyeCode(),
                cat.getFullCode(),
                cat.getBreedId(),
                cat.getFatherId(),
                cat.getMotherId(),
                cat.getId());
    }
    @Override
    public void annihilateCat(long id, long ownerId) {
        String sql ="DELETE FROM Cats WHERE CatId = ? AND ownerId = ?";
        jdbcTemplate.update(sql, id, ownerId);
    }
}
