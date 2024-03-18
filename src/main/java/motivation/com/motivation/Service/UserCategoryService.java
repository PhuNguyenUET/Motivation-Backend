package motivation.com.motivation.Service;

import motivation.com.motivation.DTO.DisplayQuoteDTO;
import motivation.com.motivation.Exceptions.NoSuchQuoteExistsException;
import motivation.com.motivation.Exceptions.NoSuchUserCategoryExistsException;
import motivation.com.motivation.Exceptions.NoSuchUserExistsException;
import motivation.com.motivation.Exceptions.UserCategoryAlreadyExistsException;
import motivation.com.motivation.Model.*;
import motivation.com.motivation.Repository.QuoteRepository;
import motivation.com.motivation.Repository.UserCategoryRepository;
import motivation.com.motivation.Repository.UserQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCategoryService {
    private UserCategoryRepository userCategoryRepository;
    private QuoteRepository quoteRepository;
    private UserQuoteRepository userQuoteRepository;

    @Autowired
    public void setQuoteRepository(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Autowired
    public void setUserQuoteRepository(UserQuoteRepository userQuoteRepository) {
        this.userQuoteRepository = userQuoteRepository;
    }

    @Autowired
    public void setUserCategoryRepository(UserCategoryRepository userCategoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
    }

    public List<UserCategory> getAllUserCategories(int userId) {
        return userCategoryRepository.findByUserId(userId);
    }

    public UserCategory getUserCategoryFromId(int categoryId) {
        Optional<UserCategory> userCategory = userCategoryRepository.findById(categoryId);
        if(userCategory.isEmpty()) {
            throw new NoSuchUserCategoryExistsException("No user category with id " + categoryId + " found");
        }
        return userCategory.get();
    }

    public UserCategory getUserCategoryFromCategory(String category) {
        return userCategoryRepository.findByCategory(category);
    }

    public List<UserCategory> getCategoriesFromSearch(String input) {
        return userCategoryRepository.findByCategoryStartingWith(input);
    }

    public void deleteQuoteFromCate(DisplayQuoteDTO quoteDTO, int categoryId) {
        Optional<UserCategory> u = userCategoryRepository.findById(categoryId);
        if(u.isEmpty()) {
            throw new NoSuchUserCategoryExistsException("No user category with id " + categoryId + " found");
        }
        UserCategory userCategory = u.get();
        if(!quoteDTO.isUserCreated()) {
            Optional<Quote> q = quoteRepository.findById(quoteDTO.getId());
            if(q.isEmpty()) {
                throw new NoSuchQuoteExistsException("No quote with id " + quoteDTO.getId() + " found");
            }
            Quote quote = q.get();
            userCategory.removeQuoteFromCategory(quote);
            userCategoryRepository.save(userCategory);
        } else {
            Optional<UserQuote> q = userQuoteRepository.findById(quoteDTO.getId());
            if(q.isEmpty()) {
                throw new NoSuchQuoteExistsException("No quote with id " + quoteDTO.getId() + " found");
            }            UserQuote quote = q.get();
            userCategory.removeUserQuoteFromCategory(quote);
            userCategoryRepository.save(userCategory);
        }
    }

    public void addQuoteToCategory(DisplayQuoteDTO quoteDTO, int categoryId) {
        Optional<UserCategory> u = userCategoryRepository.findById(categoryId);
        if(u.isEmpty()) {
            throw new NoSuchUserCategoryExistsException("No user category with id " + categoryId + " found");
        }
        UserCategory userCategory = u.get();
        if(!quoteDTO.isUserCreated()) {
            Optional<Quote> q = quoteRepository.findById(quoteDTO.getId());
            if(q.isEmpty()) {
                throw new NoSuchQuoteExistsException("No quote with id " + quoteDTO.getId() + " found");
            }
            Quote quote = q.get();
            userCategory.addQuoteToCategory(quote);
            userCategoryRepository.save(userCategory);
        } else {
            Optional<UserQuote> q = userQuoteRepository.findById(quoteDTO.getId());
            if(q.isEmpty()) {
                throw new NoSuchQuoteExistsException("No quote with id " + quoteDTO.getId() + " found");
            }            UserQuote quote = q.get();
            userCategory.addUserQuoteToCategory(quote);
            userCategoryRepository.save(userCategory);
        }
    }

    public UserCategory addUserCategory(UserCategory userCategory) {
        Optional<UserCategory> u = userCategoryRepository.findById(userCategory.getId());
        if(u.isPresent()) {
            throw new UserCategoryAlreadyExistsException("User category with id " + userCategory.getId() + " already exists");
        }
        return userCategoryRepository.save(userCategory);
    }

    public void deleteUserCategoryById(int userCategoryId) {
        Optional<UserCategory> u = userCategoryRepository.findById(userCategoryId);
        if(u.isEmpty()) {
            throw new NoSuchUserCategoryExistsException("No user category with id " + userCategoryId + " found");
        }
        userCategoryRepository.deleteById(userCategoryId);
    }

    public UserCategory updateUserCategory(UserCategory userCategory, int userCategoryId) {
        Optional<UserCategory> u = userCategoryRepository.findById(userCategoryId);
        if(u.isEmpty()) {
            throw new NoSuchUserCategoryExistsException("No user category with id " + userCategoryId + " found");
        }
        return userCategoryRepository.save(userCategory);
    }
}
