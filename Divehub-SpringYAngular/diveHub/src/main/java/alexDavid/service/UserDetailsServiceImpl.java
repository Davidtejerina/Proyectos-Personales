package alexDavid.service;

import alexDavid.Auth.SignupRequest;
import alexDavid.models.User.Role;
import alexDavid.models.User.User;
import alexDavid.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Primary
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userDetailsRepository.findAll();
    }

    public User save(User user) {
        return userDetailsRepository.save(user);
    }

    public User updateUser(String email, User user) {
        User userUpdated = (User) this.loadUserByUsername(email);
        userUpdated.setName(user.getName());
        userUpdated.setSurnames(user.getSurnames());
        userUpdated.setBirthday(user.getBirthday());
        userUpdated.setPhone(user.getPhone());
        userUpdated.setAddress(user.getAddress());
        userUpdated.setLevel(user.getLevel());
        userDetailsRepository.save(userUpdated);
        return userUpdated;
    }

    public User create(SignupRequest signupRequest){
        User user = new User(
                null,
                signupRequest.getNickname(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getName(),
                signupRequest.getSurnames(),
                signupRequest.getEmail(),
                signupRequest.getPhone(),
                signupRequest.getBirthday(),
                signupRequest.getAddress(),
                signupRequest.getLevel(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                Role.USER
        );
        return this.save(user);
    }

    public void updateLogin(String email) {
        User user = userDetailsRepository.findByEmail(email);
        user.setLast_login(LocalDateTime.now());
        this.save(user);
    }

    public boolean existUser(String email){
        return userDetailsRepository.existsByEmail(email);
    }

    public boolean isAdmin(String email) {
        User user = (User) this.loadUserByUsername(email);
        return user != null && user.getRole() == Role.ADMIN;
    }

    public void deleteUser(String email){
        User user = (User) this.loadUserByUsername(email);
        userDetailsRepository.delete(user);
    }

    public Boolean existsNickname(String nickname) {
        return userDetailsRepository.existsByNickname(nickname);
    }

    public Boolean existsEmail(String email) {
        return userDetailsRepository.existsByEmail(email);
    }
}