package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.Event;
import com.cvmtechnologyproject.associationportal.model.Announcement;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//Strategy pattern sayesinde:
//	•	AnnouncementStrategy → duyurular
//	•	NewsStrategy → haberler
//	•	ConferenceStrategy → konferanslar
//… gibi gibi ayrı ayrı yönetilebilir.

@Component
public class AnnouncementStrategy implements EventStrategy {

    @Override
    public void process(Event e) {
        // Ortak zorunlu alanlar
        if (e.getSubject() == null || e.getSubject().isBlank()) {
            throw new IllegalArgumentException("Subject is required");
        }
        if (e.getContent() == null || e.getContent().isBlank()) {
            throw new IllegalArgumentException("Content is required");
        }

        // Alt tipe özel alanlar (Announcement)
        if (e instanceof Announcement a) {
            // Görsel yolu opsiyonel → boş string geldiyse null yap
            if (a.getImagePath() != null && a.getImagePath().isBlank()) {
                a.setImagePath(null);
            }

            // Geçerlilik tarihi opsiyonel → varsa geçmiş olamaz
            LocalDate valid = a.getValidUntil();
            if (valid != null && valid.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Valid until date cannot be in the past");
            }
        }
    }
}