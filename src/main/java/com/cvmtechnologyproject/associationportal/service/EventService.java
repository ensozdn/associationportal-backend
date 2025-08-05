package com.cvmtechnologyproject.associationportal.service;

import com.cvmtechnologyproject.associationportal.model.Event;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);
    List<Event> getAllEvents();
    Event getEventById(Long id);
    void deleteEvent(Long id);
}