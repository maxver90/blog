package maxver90.blog.repository;

import maxver90.blog.entity.User;
import maxver90.blog.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    List<User> findAllByUserRoleId(Long userRoleId);
}
