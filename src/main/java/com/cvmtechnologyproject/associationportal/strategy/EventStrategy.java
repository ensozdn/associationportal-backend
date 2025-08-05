package com.cvmtechnologyproject.associationportal.strategy;

import com.cvmtechnologyproject.associationportal.model.Event;

public interface EventStrategy {
    void process(Event event);
}