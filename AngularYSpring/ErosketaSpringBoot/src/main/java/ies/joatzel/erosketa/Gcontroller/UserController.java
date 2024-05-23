package ies.joatzel.erosketa.Gcontroller;


import ies.joatzel.erosketa.Dto.UserDto.UserRequestDto;
import ies.joatzel.erosketa.Dto.UserDto.UserResponseDto;
import ies.joatzel.erosketa.Emapper.UserMapper;
import ies.joatzel.erosketa.Fservices.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@CrossOrigin                        // PERMITE EL INTERCAMBIO ENTRE BACKEND Y FRONTEND PUERTO DE ANGULAR
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUser(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(userMapper.toResponse(userService.loadUserByUsername(email)));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> postUser(
            @RequestBody UserRequestDto user
    ) {
        return ResponseEntity.ok(userMapper.toResponse(userService.save(userMapper.toModel(user))));
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable String email,
            @RequestBody UserRequestDto user
    ) {
        return ResponseEntity.ok(userMapper.toResponse(userService.updateUser(email, userMapper.toModel(user))));
    }
}
