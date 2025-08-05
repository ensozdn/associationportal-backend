package com.cvmtechnologyproject.associationportal.repository;

import com.cvmtechnologyproject.associationportal.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}