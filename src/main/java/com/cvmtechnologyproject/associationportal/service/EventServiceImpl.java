package com.cvmtechnologyproject.associationportal.service;

import com.cvmtechnologyproject.associationportal.model.Event;
import com.cvmtechnologyproject.associationportal.repository.EventRepository;
import com.cvmtechnologyproject.associationportal.strategy.EventStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventStrategyFactory eventStrategyFactory;

    public EventServiceImpl(EventRepository eventRepository,
                            EventStrategyFactory eventStrategyFactory) {
        this.eventRepository = eventRepository;
        this.eventStrategyFactory = eventStrategyFactory;
    }

    @Override
    public Event saveEvent(Event event) {
        // Strategy Pattern uygulaması
        eventStrategyFactory
                .getStrategy(event.getType())
                .process(event);

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}