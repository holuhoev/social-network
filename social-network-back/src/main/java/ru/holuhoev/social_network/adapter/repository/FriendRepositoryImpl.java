package ru.holuhoev.social_network.adapter.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.holuhoev.social_network.core.domain.entity.Friend;
import ru.holuhoev.social_network.core.domain.repo.FriendRepository;

import javax.annotation.Nonnull;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @SuppressWarnings("ConstantConditions")
    public boolean existsByUserIds(
            @Nonnull final UUID firstUserId, @Nonnull final UUID secondUserId
    ) {
        return jdbcTemplate.queryForObject(
                "SELECT exists( SELECT * FROM friends " +
                "WHERE (from_user_id = :first_user_id AND to_user_id = :second_user_id) " +
                "OR (from_user_id = :second_user_id AND to_user_id = :first_user_id) LIMIT 1) ",
                new MapSqlParameterSource()
                        .addValue("first_user_id", firstUserId.toString())
                        .addValue("second_user_id", secondUserId.toString()),
                Boolean.class
        );
    }


    @Override
    public void create(@Nonnull final Friend friend) {
        final String fromUserIdStr = friend.getFromUserId().toString();
        final String toUserIdStr = friend.getToUserId().toString();
        jdbcTemplate.update(
                "INSERT INTO friends (from_user_id, to_user_id) VALUES (:from_user_id, :to_user_id)",
                new MapSqlParameterSource()
                        .addValue("from_user_id", fromUserIdStr)
                        .addValue("to_user_id", toUserIdStr)
        );
    }

    @Override
    public boolean remove(@Nonnull final UUID firstUserId, @Nonnull final UUID secondUserId) {
        return jdbcTemplate.update(
                "DELETE FROM friends " +
                "WHERE (from_user_id = :first_user_id AND to_user_id = :second_user_id) " +
                "OR (from_user_id = :second_user_id AND to_user_id = :first_user_id) ",
                new MapSqlParameterSource()
                        .addValue("first_user_id", firstUserId.toString())
                        .addValue("second_user_id", secondUserId.toString())
        )> 0;
    }
}
