package ies.joatzel.erosketa.Dto.UserDto;

import ies.joatzel.erosketa.Amodels.User.Role;
import lombok.*;

@Data
public class UserRequestDto {
    private final String firstname;
    private final String lastname;
    private final String biography;
    private final String city;
    private final Integer postal_code;
    private final String country;
    private final Integer phone;
    private final String email;
    private final String password;
    private final Role role;
}
