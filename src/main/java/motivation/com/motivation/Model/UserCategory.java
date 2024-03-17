package motivation.com.motivation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "userCategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty
    String category;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "quoteCategoryLinking",
            joinColumns = @JoinColumn(name = "userCategoryId"),
            inverseJoinColumns = @JoinColumn(name = "quoteId")
    )
    List<Quote> quotes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "userQuoteCategoryLinking",
            joinColumns = @JoinColumn(name = "userCategoryId"),
            inverseJoinColumns = @JoinColumn(name = "userQuoteId")
    )
    List<UserQuote> userQuotes;

    public void addQuoteToCategory(Quote quote) {
        this.quotes.add(quote);
        quote.getAddedCategories().add(this);
    }
    public void removeQuoteFromCategory(Quote quote) {
        this.quotes.remove(quote);
        quote.getAddedCategories().remove(this);
    }
    public void addUserQuoteToCategory(UserQuote quote) {
        this.userQuotes.add(quote);
        quote.getAddedCategories().add(this);
    }
    public void removeUserQuoteFromCategory(UserQuote quote) {
        this.userQuotes.remove(quote);
        quote.getAddedCategories().remove(this);
    }
    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public List<UserQuote> getUserQuotes() {
        return userQuotes;
    }
}
