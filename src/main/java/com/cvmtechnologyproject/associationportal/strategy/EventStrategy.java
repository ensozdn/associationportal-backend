package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.Event;

public interface EventStrategy {
    void process(Event event);
}



//	AnnouncementStrategy.process() → subject/content boş mu, validUntil geçmişte mi kontrol eder.
//	NewsStrategy.process() → newsUrl boş mu kontrol eder.
//	EventServiceImpl.saveEvent() çağrıldığında → eventStrategyFactory.get(event.getType()).process(event) ile doğru strateji çalıştırılır.
//	Metot: process(Event) → her alt sınıf kendi kurallarını uygular.
//	Strategy Pattern sayesinde:
//	Esneklik ✔️
//	Temiz mimari ✔️
//	Yeni EventType eklenince sadece yeni Strategy yazılır ✔️