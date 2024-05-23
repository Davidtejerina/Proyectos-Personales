package ies.joatzel.erosketa.Fservices;

import ies.joatzel.erosketa.Amodels.User.Role;
import ies.joatzel.erosketa.Amodels.User.User;
import ies.joatzel.erosketa.Auth.SignupRequest;
import ies.joatzel.erosketa.Crepository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Primary
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }

    public User create(SignupRequest signupRequest){
        User user = new User(
                null,
                signupRequest.getFirstname(),
                signupRequest.getLastname(),
                signupRequest.getBiography(),
                signupRequest.getCity(),
                signupRequest.getPostal_code(),
                signupRequest.getCountry(),
                signupRequest.getPhone(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                Role.USER
        );
        this.save(user);
        return user;
    }

    public User save(User user){
        return userDetailsRepository.save(user);
    }

    public User updateUser(String email, User user) {
        User userUpdated = this.loadUserByUsername(email);
        userUpdated.setCity(user.getCity());
        userUpdated.setPostal_code(user.getPostal_code());
        userUpdated.setCountry(user.getCountry());
        userUpdated.setPhone(user.getPhone());
        userDetailsRepository.save(userUpdated);
        return userUpdated;
    }

    public boolean exixtsUser(String email){ return userDetailsRepository.existsByEmail(email); }
}
