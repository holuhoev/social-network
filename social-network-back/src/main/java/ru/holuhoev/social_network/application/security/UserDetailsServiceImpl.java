package ru.holuhoev.social_network.application.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.port.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<User> userOpt = userRepository.loadOptByUsername(username);

        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException("Username doesn't exists");
        }
        final User user = userOpt.get();

        return new UserDetailsImpl(user.getUserId(), user.getPassword(), user.getUsername());
    }
}
