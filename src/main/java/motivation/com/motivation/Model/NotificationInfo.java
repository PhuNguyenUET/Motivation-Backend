package motivation.com.motivation.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    User user;
    boolean notiAllowed;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public boolean isNotiAllowed() {
        return notiAllowed;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getTimeRepeated() {
        return timeRepeated;
    }

    public String getDaysRepeated() {
        return daysRepeated;
    }

    public int getNotiSoundId() {
        return notiSoundId;
    }

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    Category category;
    boolean isGeneral;
    String startTime;
    String endTime;
    int timeRepeated;
    String daysRepeated;
    int notiSoundId;
}
