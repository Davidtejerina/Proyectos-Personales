package ies.joatzel.erosketa.Crepository;

import ies.joatzel.erosketa.Amodels.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
