package ru.holuhoev.social_network.adapter.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.enums.Gender;
import ru.holuhoev.social_network.core.domain.repo.UserRepository;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert inserter;

    public UserRepositoryImpl(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.inserter = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("users");
    }


    @Nonnull
    @Override
    public Optional<User> loadOptByUsername(@Nonnull final String username) {
        return jdbcTemplate.query(
                "SELECT * FROM users WHERE username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username),
                this::mapRow
        ).stream().findFirst();
    }

    @Override
    public void create(@Nonnull final User user) {
        jdbcTemplate.update(
                "INSERT INTO users (user_id, username, password, first_name, last_name, interests, city, age, gender) " +
                "VALUES (:user_id, :username, :password, :first_name, :last_name, :interests,  :city, :age, :gender)",
                doMapping(user)
        );
    }




    private MapSqlParameterSource doMapping(final User user) {
        return new MapSqlParameterSource()
                .addValue("user_id", user.getUserId())
                .addValue("username", user.getUsername())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("password", user.getPassword())
                .addValue("age", user.getAge())
                .addValue("interests", user.getInterests())
                .addValue("gender", user.getGender().getId())
                .addValue("city", user.getCity());
    }


    private User mapRow(final ResultSet rs, final int column) throws SQLException {
        return new User(
                UUID.fromString(rs.getString("user_id")),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("age"),
                rs.getString("interests"),
                rs.getString("city"),
                Gender.byId(rs.getString("gender"))
        );
    }
}
