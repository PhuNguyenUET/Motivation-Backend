package motivation.com.motivation.Controller;

import motivation.com.motivation.Model.Category;
import motivation.com.motivation.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int cateId) {
        Category cate = categoryService.getCategoryById(cateId);
        return ResponseEntity.status(HttpStatus.OK).body(cate);
    }

    @GetMapping(path = "/getByName/{cate}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable("cate") String category) {
        Category cate = categoryService.getCategoryByName(category);
        return ResponseEntity.status(HttpStatus.OK).body(cate);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> cate = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(cate);
    }

    @GetMapping(path = "/getRandom")
    public ResponseEntity<List<Category>> getCategoryByName() {
        List<Category> cate = categoryService.getRandomCategory();
        return ResponseEntity.status(HttpStatus.OK).body(cate);
    }

    @GetMapping(path = "/getInSearch/{search}")
    public ResponseEntity<List<Category>> getCategoryInSearch(@PathVariable("search") String search) {
        List<Category> cate = categoryService.getCategoriesInSearch(search);
        return ResponseEntity.status(HttpStatus.OK).body(cate);
    }
}
