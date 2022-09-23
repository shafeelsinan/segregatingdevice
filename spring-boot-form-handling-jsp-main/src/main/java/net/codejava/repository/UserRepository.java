package net.codejava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.HisUser;



public interface UserRepository extends JpaRepository<HisUser, Long> {
    Optional<HisUser> findByEmail(String email);
    Optional<HisUser> findByUsernameOrEmail(String username, String email);
    Optional<HisUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsernameAndPassword(String username,String password);

}
