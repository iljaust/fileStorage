package com.iljaust.theapp.rest;

import com.iljaust.theapp.dto.EventDto;
import com.iljaust.theapp.dto.UserDto;
import com.iljaust.theapp.model.Event;
import com.iljaust.theapp.model.User;
import com.iljaust.theapp.service.EventService;
import com.iljaust.theapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public class EventControllerV1 {
    @RestController
    @RequestMapping(value = "/api/v1/events/")
    public class EventRestControllerV1 {
        private final EventService eventService;


        public EventRestControllerV1(EventService eventService) {
            this.eventService = eventService;
        }

        @GetMapping
        public ResponseEntity<List<Event>> getAllUsers() {
            List<Event> events = this.eventService.getAll();

            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<EventDto> eventDto = events.stream().map(event -> fromEvent(event))
                    .collect(Collectors.toList());


            return new ResponseEntity(eventDto, HttpStatus.OK);
        }

        @GetMapping(value = "{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
            Event event = eventService.findById(id);

            if (event == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            EventDto result = fromEvent(event);

            return new ResponseEntity(result, HttpStatus.OK);
        }

        @DeleteMapping(value = "{id}")
        public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Event event) {
            this.eventService.delete(event);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping(value = "/update")
        public ResponseEntity<UserDto> updateUser(@RequestBody @Valid Event event) {

            if (event == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            this.eventService.register(event);
            return new ResponseEntity(fromEvent(event), HttpStatus.OK);
        }

        private EventDto fromEvent(Event event) {
            EventDto eventDto = new EventDto();
            eventDto.setId(event.getId());
            eventDto.setUser(event.getUser());
            eventDto.setAction(event.getAction());
            eventDto.setEventTime(event.getEventTime());

            return eventDto;
        }
    }
}
