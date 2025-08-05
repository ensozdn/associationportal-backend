package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.Announcement;
import com.cvmtechnologyproject.associationportal.model.Event;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementStrategy implements EventStrategy {

    @Override
    public void process(Event event) {
        if (!(event instanceof Announcement announcement)) {
            throw new IllegalArgumentException("Invalid event type for AnnouncementStrategy");
        }

        // Duyuruya özel kurallar
        if (announcement.getImagePath() == null || announcement.getImagePath().isBlank()) {
            throw new IllegalArgumentException("Image path cannot be empty.");
        }

        System.out.println("Processed Announcement Event: " + announcement.getSubject());
    }
}