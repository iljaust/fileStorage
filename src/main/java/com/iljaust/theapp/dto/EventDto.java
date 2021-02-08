package com.iljaust.theapp.dto;

import com.iljaust.theapp.model.EventAction;
import com.iljaust.theapp.model.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class EventDto {
    Long id;
    User user;
    EventAction action;
    LocalDateTime eventTime;
}
