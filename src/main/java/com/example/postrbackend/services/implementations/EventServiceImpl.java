package com.example.postrbackend.services.implementations;

import com.example.postrbackend.data.entities.Event;
import com.example.postrbackend.data.entities.User;
import com.example.postrbackend.data.repositories.EventRepository;
import com.example.postrbackend.data.repositories.UserRepository;
import com.example.postrbackend.data.entities.PushNotificationRequest;
import com.example.postrbackend.services.PushNotificationService;
import com.example.postrbackend.services.EventService;
import com.example.postrbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.round;

@Service
public class EventServiceImpl implements EventService,Utils {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PushNotificationService pushNotificationService;

	@Override
	public List<Event> getAll(){
		return eventRepository.findAll()
				.stream()
				.filter(
						e -> e.getExpirationTimestamp() !=null &&
								e.getExpirationTimestamp().after(getCurrentTimestamp()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Event> getEventsInRange(
			Authentication authentication,
			Double lat,
			Double lon,
			Integer range){
		User user = extractUser(authentication,userRepository);
		user.setUserLongitude(lon);
		user.setUserLatitude(lat);
		user.setRange(range);
		List<Event> eventList = eventRepository.findAll()
				.stream()
				.filter(
						e -> e.getExpirationTimestamp() !=null &&
								e.getExpirationTimestamp().after(getCurrentTimestamp()))
				.collect(Collectors.toList());
		List<Event> outList = new ArrayList<>();
		for (Event event : eventList) {
			long distance = round(distanceBetweenEventAndUser(event,user)*1000);
			if (distance < user.getRange() * 1000) {
				outList.add(event);
			}
		}
		return outList;
	}

	@Override
	public List<Event> getEventsPage(Integer pageId) {
		Pageable page = PageRequest.of(pageId, 10);
		return  eventRepository.findAllByCreationTimestampBeforeExpirationTimestamp(page);
	}

	@Override
	public Event addEvent(Authentication authentication,Event event) {
		event.setUser(extractUser(authentication,userRepository));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_WEEK, 7);
			Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
			event.setExpirationTimestamp(timestamp);
			event.setCreationTimestamp(getCurrentTimestamp());
			sendPushAboutNewEvent(event);
			return eventRepository.save(event);
	}

	@Override
	public Event editEvent(Event eventRequest) {
		return eventRepository.findById(eventRequest.getEventId())
				.map(event -> {
					event.setEventName(eventRequest.getEventName());
					event.setContent(eventRequest.getContent());
					event.setImage(eventRequest.getImage());
					return eventRepository.save(event);
				}).get();
	}

	public void sendPushAboutNewEvent(Event event) {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (!user.getUserId().equals(event.getUser().getUserId())) {
				long distance = round(distanceBetweenEventAndUser(event,user)*1000);
				if (distance < user.getRange() * 1000) {
					pushNotificationService.sendPushNotificationToToken(new PushNotificationRequest(
							"New event in your area",
							event.getEventName() + " " + distance + "m from yours localization",
							"Event",
							user.getFirebaseRegToken()));
				}
			}
		}
	}
}
