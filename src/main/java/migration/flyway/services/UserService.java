package migration.flyway.services;

import migration.flyway.dao.UserDao;
import migration.flyway.dto.ResponseStructure;
import migration.flyway.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public ResponseEntity<ResponseStructure<User>> login(String phone, String password) {
        ResponseStructure<User> responseStructure = new ResponseStructure<>();
        User user = userDao.findByPhone(phone);
        if (user == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("User not found with Email: " + phone);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
        }
        if (password == null) {
            responseStructure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Password not set for user: " + phone);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseStructure);
        }
        if (!user.getPassword().equals(password)) {
            responseStructure.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseStructure.setMessage("Incorrect password for user: " + phone);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseStructure);
        }
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Login successful for user: " + phone);
        responseStructure.setData(user);
        return ResponseEntity.ok(responseStructure);
    }
}
