package motivation.com.motivation.Service;

import motivation.com.motivation.DTO.FavouriteQuoteDTO;
import motivation.com.motivation.DTO.NotificationQuoteDTO;
import motivation.com.motivation.Model.*;
import motivation.com.motivation.Repository.CategoryRepository;
import motivation.com.motivation.Repository.QuoteRepository;
import motivation.com.motivation.Repository.UserQuoteRepository;
import motivation.com.motivation.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuoteService {
    private QuoteRepository quoteRepository;
    private CategoryRepository categoryRepository;
    private UserQuoteRepository userQuoteRepository;
    private UserRepository userRepository;

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
    public void setUserCategory(UserRepository userRepository) {
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

    public void changeFavouriteQuote(int quoteId, int userId) {
        Optional<User> u = userRepository.findById(userId);
        //TODO: Implement exceptions
        User user = u.get();
        boolean isFav = user.checkIsFavouriteQuote(quoteId);
        Optional<Quote> q = quoteRepository.findById(quoteId);
        //TODO: Implement exceptions
        Quote quote = q.get();
        if (isFav) {
            user.removeFavouriteQuote(quote);
            userRepository.save(user);
        } else {
            user.addFavouriteQuote(quote);
            userRepository.save(user);
        }
    }

    public void changeFavouriteUserQuote(int userQuoteId) {
        Optional<UserQuote> q = userQuoteRepository.findById(userQuoteId);
        //TODO: Implement when q is empty using exceptions
        UserQuote quote = q.get();
        quote.changeFavourite();
        userQuoteRepository.save(quote);
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

    public List<FavouriteQuoteDTO> getAllFavouriteQuotes(int userId) {
        List<FavouriteQuoteDTO> favouriteList = new ArrayList<>();
        Optional<User> u = userRepository.findById(userId);
        //TODO: Implement exceptions
        User user = u.get();
        List<FavouriteQuoteDTO> quoteList = user.getFavouriteQuotes().stream().map(q -> this.modelMapper.map(q, FavouriteQuoteDTO.class)).toList();
        List<FavouriteQuoteDTO> userQuoteList = userQuoteRepository.findByUserIdAndIsFavourite(userId, true).stream().map(q -> this.modelMapper.map(q, FavouriteQuoteDTO.class)).toList();
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
        //TODO: Implement excaption
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
}

