package motivation.com.motivation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty
    private String category;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Quote> quotes;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }
}
