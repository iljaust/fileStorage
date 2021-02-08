package com.iljaust.theapp.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "event_action")
    private EventAction action;
    @CreatedDate
    @Column(name = "date")
    private LocalDateTime eventTime;
}
