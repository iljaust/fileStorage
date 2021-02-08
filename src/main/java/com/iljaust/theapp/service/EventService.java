package com.iljaust.theapp.service;

import com.iljaust.theapp.model.Event;
import com.iljaust.theapp.model.User;

import java.util.List;

public interface EventService {
    Event register(Event event);

    List<Event> getAll();

    Event findById(Long id);

    void delete(Event event);
}
