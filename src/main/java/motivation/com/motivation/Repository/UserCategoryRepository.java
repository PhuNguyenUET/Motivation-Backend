package motivation.com.motivation.Repository;

import motivation.com.motivation.Model.Category;
import motivation.com.motivation.Model.Quote;
import motivation.com.motivation.Model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Integer> {
    UserCategory findByCategory(String category);

    List<UserCategory> findByUserId(int userId);

    List<UserCategory> findByCategoryStartingWith(String prefix);
}
