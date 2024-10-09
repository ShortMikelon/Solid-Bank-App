package kz.asetkenes.solidbankapp.controller;

import kz.asetkenes.solidbankapp.domain.user.dto.AuthenticationRequest;
import kz.asetkenes.solidbankapp.domain.user.dto.JwtAuthenticationResponse;
import kz.asetkenes.solidbankapp.services.user.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("authenticate")
    public ResponseEntity<JwtAuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        String jwt = authService.authenticate(request);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("register")
    public ResponseEntity<JwtAuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        String jwt = authService.register(request);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
        return ResponseEntity.ok(response);
    }

}
