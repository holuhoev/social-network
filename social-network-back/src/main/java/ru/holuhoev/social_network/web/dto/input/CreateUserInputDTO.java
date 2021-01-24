package ru.holuhoev.social_network.web.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInputDTO {
    @Nonnull
    private String username;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    private int age;
    @Nonnull
    private String password;
    @Nonnull
    private String interests;
    @Nonnull
    private String city;

}
