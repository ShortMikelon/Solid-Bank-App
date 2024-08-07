package kz.asetkenes.solidbankapp.services.user;

import kz.asetkenes.solidbankapp.data.user.UserRepository;
import kz.asetkenes.solidbankapp.domain.user.model.User;
import kz.asetkenes.solidbankapp.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        return save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }
}

