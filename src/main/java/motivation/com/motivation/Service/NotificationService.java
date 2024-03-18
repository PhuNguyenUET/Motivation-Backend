package motivation.com.motivation.Service;

import motivation.com.motivation.Model.Category;
import motivation.com.motivation.Model.NotificationInfo;
import motivation.com.motivation.Model.User;
import motivation.com.motivation.Repository.CategoryRepository;
import motivation.com.motivation.Repository.NotificationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {
    private NotificationInfoRepository notificationInfoRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setNotificationInfoRepository(NotificationInfoRepository notificationInfoRepository) {
        this.notificationInfoRepository = notificationInfoRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public NotificationInfo insertNotificationInfo(NotificationInfo notificationInfo) {
        return notificationInfoRepository.save(notificationInfo);
    }

    public NotificationInfo updateNotificationInfo(NotificationInfo notificationInfo) {
        Optional<NotificationInfo> info = notificationInfoRepository.findById(notificationInfo.getId());
        // TODO: Implement exceptions
        return notificationInfoRepository.save(notificationInfo);
    }

    public void updateCategory(int userId, String cate) {
        NotificationInfo notificationInfo = notificationInfoRepository.findByUserId(userId);
        if(cate.equals("general")) {
            notificationInfo.setGeneral(true);
            notificationInfoRepository.save(notificationInfo);
            return;
        }
        Category category = categoryRepository.findByCategory(cate);
        notificationInfo.setGeneral(false);
        notificationInfo.setCategory(category);
        notificationInfoRepository.save(notificationInfo);
    }

    public void changeNotiAllowed(int userId) {
        NotificationInfo notificationInfo = notificationInfoRepository.findByUserId(userId);
        notificationInfo.changeNotiAllowed();
        notificationInfoRepository.save(notificationInfo);
    }

    public void changeStartAndEndTime(int userId, String startTime, String endTime) {
        NotificationInfo notificationInfo = notificationInfoRepository.findByUserId(userId);
        notificationInfo.setStartTime(startTime);
        notificationInfo.setEndTime(endTime);
        notificationInfoRepository.save(notificationInfo);
    }

    public void changeTimeRepeated(int userId, int timesRepeated) {
        NotificationInfo notificationInfo = notificationInfoRepository.findByUserId(userId);
        notificationInfo.setTimeRepeated(timesRepeated);
        notificationInfoRepository.save(notificationInfo);
    }

    public void updateDaysRepeated(int userId, String daysRepeated) {
        NotificationInfo notificationInfo = notificationInfoRepository.findByUserId(userId);
        notificationInfo.setDaysRepeated(daysRepeated);
        notificationInfoRepository.save(notificationInfo);
    }

    public void updateSoundId(int userId, int notificationSoundId) {
        NotificationInfo notificationInfo = notificationInfoRepository.findByUserId(userId);
        notificationInfo.setNotiSoundId(notificationSoundId);
        notificationInfoRepository.save(notificationInfo);
    }
}
