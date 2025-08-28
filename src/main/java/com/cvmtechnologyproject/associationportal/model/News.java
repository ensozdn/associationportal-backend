package com.cvmtechnologyproject.associationportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//	News → veritabanında news tablosunu temsil eder.
//	Alanlar: id, title, content, author, createdAt.

@Entity
@Getter
@Setter
public class News extends Event {

    @Column(name = "news_url")
    private String newsUrl;

    public News() {
        this.setType(EventType.NEWS); //  Enum otomatik set ediliyor
    }
}
