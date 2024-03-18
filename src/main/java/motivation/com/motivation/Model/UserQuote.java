package motivation.com.motivation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "userQuotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty
    private String quote;
    private String author;
    private boolean isFavourite = false;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userQuotes")
    List<UserCategory> addedCategories;

    public int getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public User getUser() {
        return user;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void changeFavourite() {
        isFavourite = !isFavourite;
    }

    public List<UserCategory> getAddedCategories() {
        return addedCategories;
    }
}
