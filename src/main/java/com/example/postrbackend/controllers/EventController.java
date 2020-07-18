package com.example.postrbackend.controllers;

//import com.google.gson.Gson;
import com.example.postrbackend.data.entities.Event;
import com.example.postrbackend.data.entities.User;
import com.example.postrbackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
//import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/api/")

public class EventController {

	private EventService eventService;

	@Autowired
	public EventController(EventService eventService){
		this.eventService = eventService;
	}

	@GetMapping("events")
	public ResponseEntity getAll(){
		return new ResponseEntity(eventService.getAll(),HttpStatus.OK);
	}

	@GetMapping("events/{lat}/{lon}/{range}")
	public ResponseEntity<List<Event>> getEventsInRange(
			Authentication authentication,
	                 @PathVariable(value = "lat" )Double lat,
	                 @PathVariable(value = "lon" )Double lon,
	                 @PathVariable(value = "range" )Integer range){
		return new ResponseEntity(eventService.getEventsInRange(authentication, lat,  lon, range),HttpStatus.OK);
	}

	@GetMapping("events/page/{id}")
	public ResponseEntity getEventsPage(
			@PathVariable(value = "id" )Integer pageId){
		return new ResponseEntity(eventService.getEventsPage(pageId),HttpStatus.OK);
	}


	@PostMapping("event")
	public ResponseEntity<Event> addEvent(
			Authentication authentication,
			@Valid @RequestBody Event event){
		return new ResponseEntity<>(eventService.addEvent(authentication, event),HttpStatus.OK);
	}

	@PutMapping("event")
	public ResponseEntity<User> editEvent(
			@Valid @RequestBody Event eventRequest){
		return new ResponseEntity(eventService.editEvent(eventRequest),HttpStatus.OK);
	}

}
