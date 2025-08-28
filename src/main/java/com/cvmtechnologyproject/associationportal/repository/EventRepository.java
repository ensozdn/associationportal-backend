package com.cvmtechnologyproject.associationportal.repository;

import com.cvmtechnologyproject.associationportal.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//	Event entity’si için veri erişim katmanı
//	CRUD işlemlerini otomatik sağlıyor
//	İhtiyaç halinde karmaşık sorgular (search, filter, join) için Specification API kullanılabiliyor

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
}