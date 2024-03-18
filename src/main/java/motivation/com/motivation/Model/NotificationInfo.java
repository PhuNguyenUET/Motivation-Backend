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
    boolean notiAllowed = false;
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    Category category;
    boolean isGeneral = true;
    String startTime = "8:00";
    String endTime = "22:00";
    int timeRepeated = 10;
    String daysRepeated = "1,2,3,4,5,6,7";
    int notiSoundId = 0;
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
    public void setUser(User user) {
        this.user = user;
    }

    public void changeNotiAllowed() {
        this.notiAllowed = !this.notiAllowed;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setTimeRepeated(int timeRepeated) {
        this.timeRepeated = timeRepeated;
    }

    public void setDaysRepeated(String daysRepeated) {
        this.daysRepeated = daysRepeated;
    }

    public void setNotiSoundId(int notiSoundId) {
        this.notiSoundId = notiSoundId;
    }
}
