package ru.holuhoev.social_network.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.holuhoev.social_network.core.domain.enums.AppErrorCode;
import ru.holuhoev.social_network.core.domain.exception.AppRuntimeException;
import ru.holuhoev.social_network.util.generator.FakeUsersGenerator;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.input.fake.GenerateFakeUsersInputDTO;

import javax.annotation.Nonnull;

@RestController
@RequestMapping("/api/fake/users")
@AllArgsConstructor
public class FakeUsersController {

    private final FakeUsersGenerator fakeUsersGenerator;

    @PostMapping("/generate")
    public BaseDTO<?> generateUsers(@RequestBody @Nonnull final GenerateFakeUsersInputDTO inputDTO) {
        if (inputDTO.getCount() > 1_000_000) {
            throw new AppRuntimeException(AppErrorCode.BAD_PARAMS);
        }

        fakeUsersGenerator.generateAndSaveUsers(inputDTO.getCount());

        return BaseDTO.success();
    }
}
