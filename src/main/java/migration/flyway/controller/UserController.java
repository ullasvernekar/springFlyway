package migration.flyway.controller;


import migration.flyway.dto.ResponseStructure;
import migration.flyway.dto.User;
import migration.flyway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login/{email}/{password}")
    public ResponseEntity<ResponseStructure<User>> login(@PathVariable String email, @PathVariable String password) {
        return userService.login(email, password);
    }

}
