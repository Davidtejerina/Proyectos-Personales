package alexDavid.controller;

import alexDavid.Auth.JwtService;
import alexDavid.Auth.LoginRequest;
import alexDavid.Auth.SignupRequest;
import alexDavid.models.User.User;
import alexDavid.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/divehub/auth")
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
        userDetailsService.updateLogin(loginRequest.getEmail());
        return ResponseEntity.ok(Map.of("token", jwtService.createToken(loginRequest.getEmail())));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if(userDetailsService.existUser(signupRequest.getEmail())) return ResponseEntity.badRequest().build();
        User user= userDetailsService.create(signupRequest);
        return ResponseEntity.ok(Map.of("token", jwtService.createToken(user.getEmail())));
    }
}
