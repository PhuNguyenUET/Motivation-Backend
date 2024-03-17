package motivation.com.motivation.Repository;

import motivation.com.motivation.Model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    @Query("SELECT q FROM Quote q ORDER BY RANDOM() LIMIT 100")
    List<Quote> get100RandomQuotes();

    @Query("SELECT q FROM Quote q where q.category.id = ?1 ORDER BY RANDOM() LIMIT 100")
    List<Quote> get100QuotesByCategory(int categoryId);

    @Query("SELECT q FROM Quote q ORDER BY RANDOM() LIMIT 1")
    Quote getRandomQuote();

    @Query("SELECT q FROM Quote q WHERE q.category.id = ?1 ORDER BY RANDOM() LIMIT 1")
    Quote getRandomQuoteFromCategoryId(int categoryId);

    List<Quote> findByCategoryId(int categoryId);
}
