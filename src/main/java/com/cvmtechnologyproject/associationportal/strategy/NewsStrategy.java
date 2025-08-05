package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.News;
import com.cvmtechnologyproject.associationportal.model.Event;
import org.springframework.stereotype.Component;

@Component
public class NewsStrategy implements EventStrategy {

    @Override
    public void process(Event event) {

        if (!(event instanceof News news)) {
            throw new IllegalArgumentException("Invalid event type for NewsStrategy");
        }


        // Buraya News'e özel kurallar yazılır
        if (news.getNewsUrl() == null || news.getNewsUrl().isBlank()) {
            throw new IllegalArgumentException("News URL cannot be empty.");
        }

        System.out.println("Processed News Event: " + news.getSubject());
    }
}