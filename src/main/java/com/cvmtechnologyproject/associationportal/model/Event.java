package com.cvmtechnologyproject.associationportal.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//	Event → Etkinlik tablosunu temsil eden entity.
//	Alanlar: id, title, description, location, date, type.
//	Spring Data JPA ile repository üzerinden CRUD yapılır.

@Entity
@Table(name = "event")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "eventClass" // Alt sınıf seçiminde kullanılacak alan
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = News.class,         name = "NEWS"),
        @JsonSubTypes.Type(value = Announcement.class, name = "ANNOUNCEMENT")
})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  Enum artık "type" alanına bağlı
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    @JsonProperty("type")           // JSON'da "type" - buraya maplenecek
    @JsonAlias({"eventType"})       // "eventType" gelirse de kabul et
    private EventType type;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, length = 4000)
    private String content;

    private LocalDate validUntil;
}
