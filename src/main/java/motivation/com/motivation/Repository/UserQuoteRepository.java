package motivation.com.motivation.Repository;

import motivation.com.motivation.Model.Quote;
import motivation.com.motivation.Model.UserQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserQuoteRepository extends JpaRepository<UserQuote, Integer> {
    List<UserQuote> findByUserId(int userId);
    List<UserQuote> findByUserIdAndIsFavourite(int userId, boolean isFavourite);
    @Query("SELECT q FROM UserQuote q ORDER BY RANDOM() LIMIT 1")
    UserQuote getRandomUserQuote();
    int countByUserId(int userId);

    int countByUserIdAndIsFavourite(int userId, boolean isFavourite);
}
