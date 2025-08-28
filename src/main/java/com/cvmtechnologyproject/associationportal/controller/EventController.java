package com.cvmtechnologyproject.associationportal.controller;

import com.cvmtechnologyproject.associationportal.model.Event;
import com.cvmtechnologyproject.associationportal.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

//1.	DTO kullanımı:
//      Şu an controller doğrudan model.Event (entity) döndürüyor. Dış dünyaya DTO vermek daha güvenli ve versiyonlama dostudur.
//	•	Örn: EventDto ↔ EventMapper (MapStruct)
//	•	Avantaj: Alan saklama, isim değişimi, API sürümleme kolaylığı.
//	2.	HTTP durum kodları:
//	•	Create: 201 Created + Location header
//	•	Delete: 204 No Content ✔️ doğru
//	•	Bulunamadı: 404 Not Found (global exception handler)
//	3.	Validasyon:
//	•	@RequestBody @Valid EventDto
//	•	DTO alanlarında @NotBlank, @Size, @FutureOrPresent gibi kurallar
//	•	Hataları MethodArgumentNotValidException ile yakalayıp güzel bir hata cevabı üretmek.

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // CREATE
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event saved = eventService.createEvent(event);
        return ResponseEntity.status(201).body(saved);
    }

    // READ - list
    @GetMapping(produces = "application/json")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // READ - single
    @GetMapping(value = "/{id}", produces = "application/json")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updated = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", produces = "application/json")
    public Page<Event> searchEvents(
            @RequestParam(required = false) String type,
            @RequestParam(required = false, name = "q") String keyword,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return eventService.search(type, keyword, pageable);
    }

}