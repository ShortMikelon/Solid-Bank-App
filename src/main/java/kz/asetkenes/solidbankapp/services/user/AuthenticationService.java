package kz.asetkenes.solidbankapp.services.user;

import kz.asetkenes.solidbankapp.domain.user.dto.AuthenticationRequest;
import kz.asetkenes.solidbankapp.domain.user.model.User;
import kz.asetkenes.solidbankapp.domain.user.model.UserRole;
import kz.asetkenes.solidbankapp.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public String register(AuthenticationRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(UserRole.ROLE_USER)
                .build();

        userService.create(user);

        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        authenticationManager.authenticate(authToken);

        UserDetails user = userService.userDetailsService().loadUserByUsername(request.username());

        return jwtService.generateToken(user);
    }
}