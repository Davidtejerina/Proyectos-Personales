package ies.joatzel.erosketa.Emapper;

import ies.joatzel.erosketa.Amodels.User.User;
import ies.joatzel.erosketa.Dto.UserDto.UserRequestDto;
import ies.joatzel.erosketa.Dto.UserDto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getBiography(),
                user.getCity(),
                user.getPostal_code(),
                user.getCountry(),
                user.getPhone(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public User toModel(UserRequestDto userDTO) {
        return new User(
                null,
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getBiography(),
                userDTO.getCity(),
                userDTO.getPostal_code(),
                userDTO.getCountry(),
                userDTO.getPhone(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole()
        );
    }
}
