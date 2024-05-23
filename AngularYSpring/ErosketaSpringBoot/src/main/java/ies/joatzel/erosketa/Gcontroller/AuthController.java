package ies.joatzel.erosketa.Gcontroller;

import ies.joatzel.erosketa.Amodels.User.User;
import ies.joatzel.erosketa.Auth.SignupRequest;
import ies.joatzel.erosketa.Auth.Token.JwtService;
import ies.joatzel.erosketa.Auth.LoginRequest;
import ies.joatzel.erosketa.Fservices.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin  // PERMITE EL INTERCAMBIO ENTRE BACKEND Y FRONTEND PUERTO DE ANGULAR
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest
    ) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        return ResponseEntity.ok(Map.of("token", jwtService.createToken(loginRequest.getEmail())));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody SignupRequest signupRequest
    ) {
        if(userDetailsService.exixtsUser(signupRequest.getEmail())) return ResponseEntity.badRequest().build();

        User user = userDetailsService.create(signupRequest);
        return ResponseEntity.ok(Map.of("token", jwtService.createToken(user.getEmail())));
    }

}
