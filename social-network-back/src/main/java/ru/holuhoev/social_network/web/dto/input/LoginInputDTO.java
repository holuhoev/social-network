package ru.holuhoev.social_network.web.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginInputDTO {
    private String username;
    private String password;
}
