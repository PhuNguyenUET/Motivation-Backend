package motivation.com.motivation.Repository;

import motivation.com.motivation.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
