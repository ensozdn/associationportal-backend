package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.EventType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class EventStrategyFactory {

    private final Map<EventType, EventStrategy> strategyMap = new EnumMap<>(EventType.class);

    public EventStrategyFactory(
            NewsStrategy newsStrategy,
            AnnouncementStrategy announcementStrategy
    ) {
        strategyMap.put(EventType.NEWS, newsStrategy);
        strategyMap.put(EventType.ANNOUNCEMENT, announcementStrategy);
    }

    public EventStrategy getStrategy(EventType type) {
        EventStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for event type: " + type);
        }
        return strategy;
    }
}