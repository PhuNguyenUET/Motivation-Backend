package motivation.com.motivation.Service;

import motivation.com.motivation.Model.User;
import motivation.com.motivation.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> u = userRepository.findById(user.getId());
        // TODO: Implement exceptions
        return userRepository.save(user);
    }
}
