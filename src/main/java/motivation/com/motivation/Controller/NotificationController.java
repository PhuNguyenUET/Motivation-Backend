package motivation.com.motivation.Controller;

import jakarta.validation.Valid;
import motivation.com.motivation.Model.NotificationInfo;
import motivation.com.motivation.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/notification")
public class NotificationController {
    NotificationService notificationService;

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<NotificationInfo> insertNotificationInfo(@RequestBody @Valid NotificationInfo notificationInfo) {
        NotificationInfo notiInfo = notificationService.insertNotificationInfo(notificationInfo);
        return ResponseEntity.status(HttpStatus.OK).body(notiInfo);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<NotificationInfo> updateNotificationInfo(@RequestBody @Valid NotificationInfo notificationInfo) {
        NotificationInfo notiInfo = notificationService.updateNotificationInfo(notificationInfo);
        return ResponseEntity.status(HttpStatus.OK).body(notiInfo);
    }

    @PutMapping(path = "/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestParam int userId, @RequestParam String category) {
        notificationService.updateCategory(userId, category);
        return ResponseEntity.status(HttpStatus.OK).body("Notification settings updated successfully");
    }

    @PutMapping(path = "/updateAllowance")
    public ResponseEntity<String> updateNotiAllowed(@RequestParam int userId) {
        notificationService.changeNotiAllowed(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Notification settings updated successfully");
    }

    @PutMapping(path = "/updateStartEndTime")
    public ResponseEntity<String> updateStartTimeAndEndTIme(@RequestParam int userId, @RequestParam String startTime, @RequestParam String endTime) {
        notificationService.changeStartAndEndTime(userId, startTime, endTime);
        return ResponseEntity.status(HttpStatus.OK).body("Notification settings updated successfully");
    }

    @PutMapping(path = "/updateTimeRepeated")
    public ResponseEntity<String> updateTimeRepeated(@RequestParam int userId, @RequestParam int timeRepeated) {
        notificationService.changeTimeRepeated(userId, timeRepeated);
        return ResponseEntity.status(HttpStatus.OK).body("Notification settings updated successfully");
    }

    @PutMapping(path = "/updateDaysRepeated")
    public ResponseEntity<String> updateDaysRepeated(@RequestParam int userId, @RequestParam String daysRepeated) {
        notificationService.updateDaysRepeated(userId, daysRepeated);
        return ResponseEntity.status(HttpStatus.OK).body("Notification settings updated successfully");
    }

    @PutMapping(path = "/updateNotificationSound")
    public ResponseEntity<String> updateNotificationSound(@RequestParam int userId, @RequestParam int notificationSoundId) {
        notificationService.updateSoundId(userId, notificationSoundId);
        return ResponseEntity.status(HttpStatus.OK).body("Notification settings updated successfully");
    }

}
