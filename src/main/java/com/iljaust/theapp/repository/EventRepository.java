package com.iljaust.theapp.repository;

import com.iljaust.theapp.model.Event;
import com.iljaust.theapp.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
