package motivation.com.motivation.Service;

import motivation.com.motivation.Exceptions.NoSuchUserExistsException;
import motivation.com.motivation.Exceptions.UserAlreadyExistsException;
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
        Optional<User> u = userRepository.findById(user.getId());
        if(u.isPresent()) {
            throw new UserAlreadyExistsException("User with id " + user.getId() + " already exists");
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> u = userRepository.findById(user.getId());
        if(u.isEmpty()) {
            throw new NoSuchUserExistsException("No user with id " + user.getId() + " found");
        }
        return userRepository.save(user);
    }

    public void updateName(int userId, String name) {
        Optional<User> u = userRepository.findById(userId);
        if(u.isEmpty()) {
            throw new NoSuchUserExistsException("No user with id " + userId + " found");
        }
        User user = u.get();
        user.setName(name);
        userRepository.save(user);
    }

    public void updateBackgroundId(int userId, int backgroundId) {
        Optional<User> u = userRepository.findById(userId);
        if(u.isEmpty()) {
            throw new NoSuchUserExistsException("No user with id " + userId + " found");
        }
        User user = u.get();
        user.setBackgroundId(backgroundId);
        userRepository.save(user);
    }

    public void updateFontId(int userId, int fontId) {
        Optional<User> u = userRepository.findById(userId);
        if(u.isEmpty()) {
            throw new NoSuchUserExistsException("No user with id " + userId + " found");
        }
        User user = u.get();
        user.setFontId(fontId);
        userRepository.save(user);
    }
}
