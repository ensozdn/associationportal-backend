package com.cvmtechnologyproject.associationportal.service;

import com.cvmtechnologyproject.associationportal.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    // Create akışını Strategy + save ile yapan yardımcı
    Event saveEvent(Event event);

    // Basit CRUD
    List<Event> getAllEvents();
    Event getEventById(Long id);
    void deleteEvent(Long id);
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);

    // Pagination + Sort + Filter (type, q)
    Page<Event> search(String type, String q, Pageable pageable);
}