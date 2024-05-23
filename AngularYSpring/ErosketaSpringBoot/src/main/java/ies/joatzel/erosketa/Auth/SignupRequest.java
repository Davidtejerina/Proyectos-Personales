package ies.joatzel.erosketa.Auth;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstname;
    private String lastname;
    private String biography;
    private String city;
    private Integer postal_code;
    private String country;
    private Integer phone;
    private String email;
    private String password;
}
