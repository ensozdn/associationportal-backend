package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.Event;
import com.cvmtechnologyproject.associationportal.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsStrategy implements EventStrategy {

    @Override
    public void process(Event e) {
        // Sadece News işlenir
        if (!(e instanceof News n)) {
            throw new IllegalArgumentException("NewsStrategy can only process News events");
        }

        // Ortak zorunlular
        if (n.getSubject() == null || n.getSubject().isBlank()) {
            throw new IllegalArgumentException("Subject is required");
        }
        if (n.getContent() == null || n.getContent().isBlank()) {
            throw new IllegalArgumentException("Content is required");
        }

        // NEWS'e özel: URL zorunlu
        if (n.getNewsUrl() == null || n.getNewsUrl().isBlank()) {
            throw new IllegalArgumentException("News URL is required");
        }


        n.setValidUntil(null);     // Event üzerinden var; kalabilir
        // n.setImagePath(null);   // <-- YOK; bu satırı kaldırdık

        // Normalize (trim)
        n.setSubject(n.getSubject().trim());
        n.setContent(n.getContent().trim());
        n.setNewsUrl(n.getNewsUrl().trim());
    }
}