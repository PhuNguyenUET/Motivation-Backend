package motivation.com.motivation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotEmpty
    String name;

    int backgroundId = 0;

    int fontId = 0;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "favouriteQuotes",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "quoteId")
    )
    List<Quote> favouriteQuotes;

    @OneToOne(mappedBy = "user")
    NotificationInfo notificationInfo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public int getFontId() {
        return fontId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public void setFontId(int fontId) {
        this.fontId = fontId;
    }

    public List<Quote> getFavouriteQuotes() {
        return favouriteQuotes;
    }

    public NotificationInfo getNotificationInfo() {
        return notificationInfo;
    }

    public void addFavouriteQuote(Quote quote) {
        this.favouriteQuotes.add(quote);
        quote.getLikedUsers().add(this);
    }

    public boolean checkIsFavouriteQuote(int quoteId) {
        Quote quote = this.favouriteQuotes.stream().filter(q -> q.getId() == quoteId).findFirst().orElse(null);
        return quote != null;
    }

    public void removeFavouriteQuote(Quote quote) {
        this.favouriteQuotes.remove(quote);
        quote.getLikedUsers().remove(this);
    }
}
