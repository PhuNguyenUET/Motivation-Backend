package motivation.com.motivation.Service;

import motivation.com.motivation.Exceptions.NoSuchCategoryExistsException;
import motivation.com.motivation.Exceptions.NoSuchUserCategoryExistsException;
import motivation.com.motivation.Model.Category;
import motivation.com.motivation.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(int cateId) {
        if (cateId == 0) {
            return new Category(0, "general");
        }
        Optional<Category> category = categoryRepository.findById(cateId);
        if(category.isEmpty()) {
            throw new NoSuchCategoryExistsException("No category with id " + cateId + " found");
        }
        return category.get();
    }

    public Category getCategoryByName(String cateName) {
        if (cateName.equals("general")) {
            return new Category(0, "general");
        }
        return categoryRepository.findByCategory(cateName);
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(0, "general"));
        categories.addAll(categoryRepository.findAll());
        return categories;
    }

    public List<Category> getRandomCategory() {
        return categoryRepository.getRandomCategories();
    }

    public List<Category> getCategoriesInSearch(String search) {
        return categoryRepository.findByCategoryStartingWith(search);
    }
}
