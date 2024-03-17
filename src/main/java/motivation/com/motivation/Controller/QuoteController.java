package motivation.com.motivation.Controller;

import jakarta.validation.Valid;
import motivation.com.motivation.DTO.FavouriteQuoteDTO;
import motivation.com.motivation.DTO.NotificationQuoteDTO;
import motivation.com.motivation.Model.Quote;
import motivation.com.motivation.Model.User;
import motivation.com.motivation.Model.UserQuote;
import motivation.com.motivation.Service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/quote")
public class QuoteController {
    private QuoteService quoteService;

    @Autowired
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<UserQuote> insertQuote(@RequestBody @Valid UserQuote userQuote) {
        UserQuote q = quoteService.insertUserQuote(userQuote);
        return ResponseEntity.status(HttpStatus.OK).body(q);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<UserQuote> updateQuote(@RequestBody @Valid UserQuote userQuote, @PathVariable("id") int userQuoteId) {
        UserQuote q = quoteService.updateUserQuote(userQuote, userQuoteId);
        return ResponseEntity.status(HttpStatus.OK).body(q);
    }

    @PutMapping(path = "/changeFavouriteQuote")
    public ResponseEntity<String> changeFavouriteQuote(@RequestParam int userId, @RequestParam int quoteId) {
        quoteService.changeFavouriteQuote(quoteId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Favourite list updated");
    }

    @PutMapping(path = "/changeFavouriteQuote/userQuote/{id}")
    public ResponseEntity<String> changeFavouriteUserQuote(@PathVariable("id") int userQuoteId) {
        quoteService.changeFavouriteUserQuote(userQuoteId);
        return ResponseEntity.status(HttpStatus.OK).body("Favourite list updated");
    }

    @GetMapping(path = "/getQuotes/{category}")
    public ResponseEntity<List<Quote>> getQuotesByCategory(@PathVariable("category") String category) {
        List<Quote> quotes = quoteService.getQuotesByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(quotes);
    }

    @GetMapping(path = "/getQuotes/userQuotes/{id}")
    public ResponseEntity<List<UserQuote>> getUserQuotes(@PathVariable("id") int userId) {
        List<UserQuote> userQuotes = quoteService.getUserQuotes(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userQuotes);
    }

    @GetMapping(path = "/getQuotes/favourites/{id}")
    public ResponseEntity<List<FavouriteQuoteDTO>> getFavouriteQuotes(@PathVariable("id") int userId) {
        List<FavouriteQuoteDTO> favouriteQuoteDTOS = quoteService.getAllFavouriteQuotes(userId);
        return ResponseEntity.status(HttpStatus.OK).body(favouriteQuoteDTOS);
    }

    @GetMapping(path = "/getRandomQuote")
    public ResponseEntity<NotificationQuoteDTO> getRandomQuoteFromCategory(@RequestParam String category, @RequestParam int userId) {
        NotificationQuoteDTO notificationQuote = quoteService.getRandomQuoteFromCate(category, userId);
        return ResponseEntity.status(HttpStatus.OK).body(notificationQuote);
    }

    @GetMapping(path = "/getQuote/{id}")
    public ResponseEntity<Quote> getQuoteFromId(@PathVariable("id") int quoteId) {
        Quote q = quoteService.getQuoteFromId(quoteId);
        return ResponseEntity.status(HttpStatus.OK).body(q);
    }

    @GetMapping(path = "/getUserQuote/{id}")
    public ResponseEntity<UserQuote> getUserQuoteFromId(@PathVariable("id") int quoteId) {
        UserQuote q = quoteService.getUserQuoteFromId(quoteId);
        return ResponseEntity.status(HttpStatus.OK).body(q);
    }

    @GetMapping(path = "/numOfUserCreated/{id}")
    public ResponseEntity<Integer> getNumberOfUserCreated(@PathVariable("id") int userId) {
        int num = quoteService.getNumberOfUserCreated(userId);
        return ResponseEntity.status(HttpStatus.OK).body(num);
    }

    @GetMapping(path = "/numOfFavourites/{id}")
    public ResponseEntity<Integer> getNumberOfFavourites(@PathVariable("id") int userId) {
        int num = quoteService.getNumberOfFavourites(userId);
        return ResponseEntity.status(HttpStatus.OK).body(num);
    }
}
