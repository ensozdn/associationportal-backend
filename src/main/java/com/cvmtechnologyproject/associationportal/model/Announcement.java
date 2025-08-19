package com.cvmtechnologyproject.associationportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends Event {

    @Column(name = "image_path")
    private String imagePath;

    public Announcement() {
        this.setType(EventType.ANNOUNCEMENT); //  Enum otomatik set ediliyor
    }
}
