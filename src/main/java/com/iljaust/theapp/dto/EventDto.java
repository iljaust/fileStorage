package com.iljaust.theapp.dto;

import com.iljaust.theapp.model.EventAction;
import com.iljaust.theapp.model.User;
import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Data
public class EventDto {
    Long id;
    User user;
    EventAction action;
    @PastOrPresent
    LocalDateTime eventTime;
}
