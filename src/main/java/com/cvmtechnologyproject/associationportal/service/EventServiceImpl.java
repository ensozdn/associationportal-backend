package com.cvmtechnologyproject.associationportal.service;

import com.cvmtechnologyproject.associationportal.model.Announcement;
import com.cvmtechnologyproject.associationportal.model.Event;
import com.cvmtechnologyproject.associationportal.model.News;
import com.cvmtechnologyproject.associationportal.repository.EventRepository;
import com.cvmtechnologyproject.associationportal.strategy.EventStrategyFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.cvmtechnologyproject.associationportal.service.EventSpecifications.hasType;
import static com.cvmtechnologyproject.associationportal.service.EventSpecifications.keywordLike;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventStrategyFactory eventStrategyFactory;

    public EventServiceImpl(EventRepository eventRepository,
                            EventStrategyFactory eventStrategyFactory) {
        this.eventRepository = eventRepository;
        this.eventStrategyFactory = eventStrategyFactory;
    }

    /** Create: Strategy → save */
    @Override
    public Event saveEvent(Event event) {
        // Tipe göre validasyon/zenginleştirme
        eventStrategyFactory.get(event.getType()).process(event);
        return eventRepository.save(event);
    }

    // ===== CRUD =====

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event %d not found".formatted(id)));
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Event %d not found".formatted(id));
        }
        eventRepository.deleteById(id);
    }

    @Override
    public Event createEvent(Event event) {
        return saveEvent(event);
    }

    @Override
    @Transactional
    public Event updateEvent(Long id, Event patch) {
        Event e = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event %d not found".formatted(id)));

        // Tip değişimini engelle
        if (patch.getType() != null && e.getType() != null && !e.getType().equals(patch.getType())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type cannot be changed");
        }

        // ---- Ortak alanlar (Event taban sınıfı) ----
        if (patch.getSubject() != null) e.setSubject(patch.getSubject());
        if (patch.getContent() != null) e.setContent(patch.getContent());

        // ---- Alt-tipe özgü alanlar ----
        if (e instanceof News en && patch instanceof News pn) {
            if (pn.getNewsUrl() != null) en.setNewsUrl(pn.getNewsUrl());
            // Not: News için imagePath yoksa dokunmuyoruz.
        }
        if (e instanceof Announcement ea && patch instanceof Announcement pa) {
            if (pa.getValidUntil() != null) ea.setValidUntil(pa.getValidUntil());
            if (pa.getImagePath()  != null) ea.setImagePath(pa.getImagePath());
        }

        // Değişiklik sonrası Strategy (validasyon / türetilmiş alanlar)
        eventStrategyFactory.get(e.getType()).process(e);

        return eventRepository.save(e);
    }


    @Override
    public Page<Event> search(String type, String q, Pageable pageable) {

        Specification<Event> spec = Specification.allOf(
                hasType(type),
                keywordLike(q)
        );
        return eventRepository.findAll(spec, pageable);
    }
}