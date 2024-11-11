package migration.flyway.dao;


import migration.flyway.dto.User;
import migration.flyway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }


}
