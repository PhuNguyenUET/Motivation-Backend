package motivation.com.motivation.Controller;

import jakarta.validation.Valid;
import motivation.com.motivation.DTO.DisplayQuoteDTO;
import motivation.com.motivation.Model.UserCategory;
import motivation.com.motivation.Service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/userCategory")
public class UserCategoryController {
    private UserCategoryService userCategoryService;

    @Autowired
    public void setUserCategoryService(UserCategoryService userCategoryService) {
        this.userCategoryService = userCategoryService;
    }

    @GetMapping(path = "/getAll/{userId}")
    public ResponseEntity<List<UserCategory>> getAllUserCategories(@PathVariable("userId") int userId) {
        List<UserCategory> userCategories = userCategoryService.getAllUserCategories(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userCategories);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<UserCategory> getUserCategoryFromId(@PathVariable("id") int categoryId) {
        UserCategory userCategory = userCategoryService.getUserCategoryFromId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(userCategory);
    }

    @GetMapping(path = "/getByName/{category}")
    public ResponseEntity<UserCategory> getUserCategoryFromCategory(@PathVariable("category") String category) {
        UserCategory userCategory = userCategoryService.getUserCategoryFromCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(userCategory);
    }

    @GetMapping(path = "/getInSearch/{input}")
    public ResponseEntity<List<UserCategory>> getUserCategoryFromSearch(@PathVariable("input") String input) {
        List<UserCategory> userCategories = userCategoryService.getCategoriesFromSearch(input);
        return ResponseEntity.status(HttpStatus.OK).body(userCategories);
    }

    @PutMapping(path = "/deleteQuoteFromCate/{categoryId}")
    public ResponseEntity<String> deleteQuoteFromCategory(@PathVariable("categoryId") int categoryId, @RequestBody @Valid DisplayQuoteDTO displayQuoteDTO) {
        userCategoryService.deleteQuoteFromCate(displayQuoteDTO, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("Category updated successfully");
    }

    @PutMapping(path = "/addQuoteFromCate/{categoryId}")
    public ResponseEntity<String> addQuoteFromCategory(@PathVariable("categoryId") int categoryId, @RequestBody @Valid DisplayQuoteDTO displayQuoteDTO) {
        userCategoryService.addQuoteToCategory(displayQuoteDTO, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("Category updated successfully");
    }

    @DeleteMapping(path = "/deleteQuoteFromCate/{id}")
    public ResponseEntity<String> deleteQuoteFromCategory(@PathVariable("id") int id) {
        userCategoryService.deleteUserCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }

    @PostMapping(path = "/add")
    public ResponseEntity<UserCategory> addUserCategory(@RequestBody @Valid UserCategory userCategory) {
        UserCategory u = userCategoryService.addUserCategory(userCategory);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<UserCategory> updateUserCategory(@RequestBody @Valid UserCategory userCategory, @PathVariable("id") int userCategoryId) {
        UserCategory u = userCategoryService.updateUserCategory(userCategory, userCategoryId);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
}
