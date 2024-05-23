package alexDavid.dtos.UserDTO;

import alexDavid.models.Level;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class UserRequestDto {
    private final String nickname;
    private final String password;
    private final String name;
    private final String surnames;
    private final String email;
    private final Integer phone;
    private final LocalDateTime birthday;
    private final String address;
    private final Level level;
}
