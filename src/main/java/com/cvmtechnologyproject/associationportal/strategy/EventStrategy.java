package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.Event;

public interface EventStrategy {
    void process(Event event);
}


//Örneğin:
//	•	AnnouncementStrategy.process() → subject/content boş mu, validUntil geçmişte mi kontrol eder.
//	•	NewsStrategy.process() → newsUrl boş mu kontrol eder.
//	•	EventServiceImpl.saveEvent() çağrıldığında → eventStrategyFactory.get(event.getType()).process(event) ile doğru strateji çalıştırılır.