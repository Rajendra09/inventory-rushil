// src/main/java/im/sma/bysma/springbootsqlite/repository/InventoryUserRepository.java
package im.sma.bysma.springbootsqlite.repository;

import im.sma.bysma.springbootsqlite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByEmail(String email);
}