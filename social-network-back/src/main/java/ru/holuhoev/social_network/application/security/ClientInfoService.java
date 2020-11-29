package ru.holuhoev.social_network.application.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
public class ClientInfoService {

    @Nonnull
    public UserDetailsImpl loadClientInfo() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
