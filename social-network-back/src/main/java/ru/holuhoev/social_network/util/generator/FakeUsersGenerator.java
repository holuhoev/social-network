package ru.holuhoev.social_network.util.generator;


import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.enums.Gender;
import ru.holuhoev.social_network.core.usecase.CreateUser;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class FakeUsersGenerator {

    private final Faker faker = new Faker();
    private final CreateUser createUser;

    public FakeUsersGenerator(final CreateUser createUser) {
        this.createUser = createUser;
    }

    public void generateAndSaveUsers(final int count) {
        final int batchSize = 100;

        final DecimalFormat o = new DecimalFormat("##.##");

        final int batchesCount = count / batchSize;

        log.info("Run {} tasks", batchesCount);
        CompletableFuture.supplyAsync(() -> {
            log.info("Generate started");
            IntStream.range(0, batchesCount)
                     .forEach((i) -> {
                         generateAndSave(batchSize);
                         log.info("Done {} % ({})", o.format(((double) i / batchesCount) * 100), i);
                     });

            log.info("Generate Done");
            return null;
        });

    }


    private void generateAndSave(final int count) {
        final List<User> fakeUsers =
                IntStream.range(0, count)
                         .boxed()
                         .map(n -> fakeUser())
                         .collect(Collectors.toList());

        createUser.createBatch(fakeUsers);
    }

    public User fakeUser() {

        final String username = String.format(
                "%s_%s",
                faker.name().username(),
                RandomStringUtils.randomAlphanumeric(7)
        );

        return new User(
                UUID.randomUUID(),
                username,
                RandomStringUtils.randomAlphanumeric(7),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(18, 45),
                faker.job().keySkills(),
                faker.address().city(),
                Gender.MALE
        );
    }
}
