package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.EventType;
import org.springframework.stereotype.Component;

@Component
public class EventStrategyFactory {

    private final AnnouncementStrategy announcementStrategy;
    private final NewsStrategy newsStrategy;

    public EventStrategyFactory(AnnouncementStrategy announcementStrategy,
                                NewsStrategy newsStrategy) {
        this.announcementStrategy = announcementStrategy;
        this.newsStrategy = newsStrategy;
    }

    public EventStrategy get(EventType type) {
        if (type == null) {
            throw new IllegalArgumentException("Event type is required");
        }
        return switch (type) {
            case ANNOUNCEMENT -> announcementStrategy;
            case NEWS -> newsStrategy;
        };
    }
}