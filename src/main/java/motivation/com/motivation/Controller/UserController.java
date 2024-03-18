package motivation.com.motivation.Controller;

import jakarta.validation.Valid;
import motivation.com.motivation.Model.User;
import motivation.com.motivation.Model.UserQuote;
import motivation.com.motivation.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<User> insertUser(@RequestBody @Valid User user) {
        User u = userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        User u = userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    @PutMapping(path = "/updateName")
    public ResponseEntity<String> changeName(@RequestParam int userId, @RequestParam String name) {
        userService.updateName(userId, name);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

    @PutMapping(path = "/updateBackgroundId")
    public ResponseEntity<String> changeBackgroundId(@RequestParam int userId, @RequestParam int backgroundId) {
        userService.updateBackgroundId(userId, backgroundId);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

    @PutMapping(path = "/updateFontId")
    public ResponseEntity<String> changeFontId(@RequestParam int userId, @RequestParam int fontId) {
        userService.updateFontId(userId, fontId);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }
}
