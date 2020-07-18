package com.example.postrbackend.services;

import com.example.postrbackend.data.entities.Event;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface EventService {
	List<Event> getAll();
	List<Event> getEventsInRange(Authentication authentication, Double lat, Double lon, Integer range);
	List<Event> getEventsPage(Integer pageId);
	Event addEvent(Authentication authentication,Event event);
	Event editEvent(Event event);
}
