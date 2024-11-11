package migration.flyway.repository;

import migration.flyway.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByPhone(String phone);
}
