package motivation.com.motivation.Repository;

import motivation.com.motivation.Model.NotificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationInfoRepository extends JpaRepository<NotificationInfo, Integer> {
    NotificationInfo findByUserId(int userId);
}
