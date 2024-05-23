package alexDavid.mappers;

import alexDavid.dtos.UserDTO.UserRequestDto;
import alexDavid.dtos.UserDTO.UserResponseDto;
import alexDavid.models.User.User;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class UserMapper {
    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getNickname(),
                user.getPassword(),
                user.getName(),
                user.getSurnames(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthday(),
                user.getAddress(),
                user.getLevel(),
                user.getRegistration_date(),
                user.getLast_login(),
                user.getRole()
        );
    }

    public List<UserResponseDto> toResponse(List<User> user) {
        return user.stream() .map(this::toResponse) .toList();
    }

    public User toModel(UserRequestDto userDto){
        return new User(
                null,
                userDto.getNickname(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getSurnames(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getBirthday(),
                userDto.getAddress(),
                userDto.getLevel(),
                null,
                null,
                null
        );
    }
}
