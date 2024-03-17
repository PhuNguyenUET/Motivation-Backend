package motivation.com.motivation.Repository;

import motivation.com.motivation.Model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Integer> {
}
