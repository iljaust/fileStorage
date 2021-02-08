package com.iljaust.theapp.service.impl;

import com.iljaust.theapp.model.Event;
import com.iljaust.theapp.repository.EventRepository;
import com.iljaust.theapp.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event register(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);

    }
}
