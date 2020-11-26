package ru.holuhoev.social_network.web.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInput {
    @Nonnull
    private String username;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    @Nonnull
    private LocalDate birthDate;
    @Nonnull
    private String password;
    @Nonnull
    private String interests;
    @Nonnull
    private String city;

}
