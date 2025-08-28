package com.cvmtechnologyproject.associationportal.service;

import com.cvmtechnologyproject.associationportal.model.Event;
import org.springframework.data.jpa.domain.Specification;

//filtreleme search sistemi içinde kullanılıyor dinamik esnek arama sağlar

public class EventSpecifications {

    public static Specification<Event> hasType(String type) {
        if (type == null || type.isBlank()) return null;
        return (root, query, cb) -> cb.equal(cb.upper(root.get("type")), type.toUpperCase());
    }

    public static Specification<Event> keywordLike(String qStr) {
        if (qStr == null || qStr.isBlank()) return null;
        String like = "%" + qStr.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("subject")), like),
                cb.like(cb.lower(root.get("content")), like)
        );
    }
}