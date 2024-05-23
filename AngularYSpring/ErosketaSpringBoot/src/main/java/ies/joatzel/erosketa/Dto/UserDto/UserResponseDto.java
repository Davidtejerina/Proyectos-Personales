package ies.joatzel.erosketa.Dto.UserDto;


import lombok.*;

@Data
public class UserResponseDto {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String biography;
    private final String city;
    private final Integer postal_code;
    private final String country;
    private final Integer phone;
    private final String email;
    private final String role;
}
