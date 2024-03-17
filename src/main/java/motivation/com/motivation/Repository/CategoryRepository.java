package motivation.com.motivation.Repository;

import motivation.com.motivation.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategory(String category);
    @Query("SELECT c FROM Category c ORDER BY RANDOM() LIMIT 9")
    List<Category> getRandomCategories();

    List<Category> findByCategoryStartingWith(String prefix);
}
