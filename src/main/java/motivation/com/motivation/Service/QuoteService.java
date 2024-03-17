package motivation.com.motivation.Service;

import motivation.com.motivation.DTO.DisplayQuoteDTO;
import motivation.com.motivation.DTO.NotificationQuoteDTO;
import motivation.com.motivation.Model.*;
import motivation.com.motivation.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {
    private QuoteRepository quoteRepository;
    private CategoryRepository categoryRepository;
    private UserQuoteRepository userQuoteRepository;
    private UserRepository userRepository;
    private UserCategoryRepository userCategoryRepository;

    @Autowired
    public void setUserCategoryRepository(UserCategoryRepository userCategoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
    }
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public void setQuoteRepository(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setUserQuoteRepository(UserQuoteRepository userQuoteRepository) {
        this.userQuoteRepository = userQuoteRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserQuote insertUserQuote(UserQuote quote) {
        return userQuoteRepository.save(quote);
    }

    public void deleteUserQuoteById(int quoteId) {
        Optional<UserQuote> q = userQuoteRepository.findById(quoteId);
        if (q.isEmpty()) {
            return;
        }
        UserQuote quote = q.get();

        userQuoteRepository.deleteById(quoteId);
    }

    public UserQuote updateUserQuote(UserQuote quote, int quoteId) {
        Optional<UserQuote> q = userQuoteRepository.findById(quoteId);
        //TODO: Implement when q is empty using exceptions
        return userQuoteRepository.save(quote);
    }

    public void changeFavouriteQuote(DisplayQuoteDTO displayQuote, int userId) {
        if(!displayQuote.isUserCreated()) {
            Optional<User> u = userRepository.findById(userId);
            //TODO: Implement exceptions
            User user = u.get();
            boolean isFav = user.checkIsFavouriteQuote(displayQuote.getId());
            Optional<Quote> q = quoteRepository.findById(displayQuote.getId());
            //TODO: Implement exceptions
            Quote quote = q.get();
            if (isFav) {
                user.removeFavouriteQuote(quote);
                userRepository.save(user);
            } else {
                user.addFavouriteQuote(quote);
                userRepository.save(user);
            }
        } else {
            Optional<UserQuote> q = userQuoteRepository.findById(displayQuote.getId());
            //TODO: Implement when q is empty using exceptions
            UserQuote quote = q.get();
            quote.changeFavourite();
            userQuoteRepository.save(quote);
        }
    }

    public List<Quote> getQuotesByCategory(String category) {
        Category c = categoryRepository.findByCategory(category);
        if (category.equals("general")) {
            return getRandomQuotes();
        }
        return quoteRepository.get100QuotesByCategory(c.getId());
    }

    public List<UserQuote> getUserQuotes(int userId) {
        return userQuoteRepository.findByUserId(userId);
    }

    private List<Quote> getRandomQuotes() {
        return quoteRepository.get100RandomQuotes();
    }

    public List<DisplayQuoteDTO> getAllFavouriteQuotes(int userId) {
        List<DisplayQuoteDTO> favouriteList = new ArrayList<>();
        Optional<User> u = userRepository.findById(userId);
        //TODO: Implement exceptions
        User user = u.get();
        List<DisplayQuoteDTO> quoteList = user.getFavouriteQuotes().stream().map(q -> this.modelMapper.map(q, DisplayQuoteDTO.class)).toList();
        List<DisplayQuoteDTO> userQuoteList = userQuoteRepository.findByUserIdAndIsFavourite(userId, true).stream().map(q -> this.modelMapper.map(q, DisplayQuoteDTO.class)).toList();
        favouriteList.addAll(userQuoteList);
        favouriteList.addAll(quoteList);
        return favouriteList;
    }

    public int getNumberOfFavourites(int userId) {
        Optional<User> u = userRepository.findById(userId);
        //TODO: Implement exceptions
        User user = u.get();
        int quoteListCount = user.getFavouriteQuotes().size();
        int userQuoteListCount = userQuoteRepository.countByUserIdAndIsFavourite(userId, true);
        return quoteListCount + userQuoteListCount;
    }

    public int getNumberOfUserCreated(int userId) {
        return userQuoteRepository.countByUserId(userId);
    }

    public Quote getQuoteFromId(int id) {
        Optional<Quote> q = quoteRepository.findById(id);
        //TODO: Implement exception
        return q.get();
    }

    public UserQuote getUserQuoteFromId(int id) {
        Optional<UserQuote> q = userQuoteRepository.findById(id);
        //TODO: Implement exception
        return q.get();
    }

    public NotificationQuoteDTO getRandomQuoteFromCate(String category, int userId) {
        Category c = categoryRepository.findByCategory(category);
        if (category.equals("general")) {
            Quote q = quoteRepository.getRandomQuote();
            return this.modelMapper.map(q, NotificationQuoteDTO.class);
        } else if (category.equals("user-created")) {
            UserQuote q = userQuoteRepository.getRandomUserQuote();
            return this.modelMapper.map(q, NotificationQuoteDTO.class);
        } else {
            Quote q = quoteRepository.getRandomQuoteFromCategoryId(c.getId());
            return this.modelMapper.map(q, NotificationQuoteDTO.class);
        }
    }

    public List<DisplayQuoteDTO> getQuotesByUserCategory(int categoryId) {
        Optional<UserCategory> c = userCategoryRepository.findById(categoryId);
        //TODO: Implement exceptions
        UserCategory userCategory = c.get();
        List<DisplayQuoteDTO> quotes = new ArrayList<>();
        List<DisplayQuoteDTO> quoteList = userCategory.getQuotes().stream().map(q -> this.modelMapper.map(q, DisplayQuoteDTO.class)).toList();
        List<DisplayQuoteDTO> userQuoteList = userCategory.getUserQuotes().stream().map(q -> this.modelMapper.map(q, DisplayQuoteDTO.class)).toList();
        quotes.addAll(userQuoteList);
        quotes.addAll(quoteList);
        return quotes;
    }
}

