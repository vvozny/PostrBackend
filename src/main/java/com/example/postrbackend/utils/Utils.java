package com.example.postrbackend.utils;


import com.example.postrbackend.data.entities.Event;
import com.example.postrbackend.data.entities.User;
import com.example.postrbackend.data.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.sql.Timestamp;

public interface Utils {

	default User extractUser(Authentication authentication, UserRepository userRepository){
		Jwt claims = (Jwt) authentication.getCredentials();
		String firebaseId = claims.getClaim("user_id").toString();
		return userRepository.findByFirebaseUid(firebaseId);
	}

	default Timestamp getCurrentTimestamp() {
		long millis=System.currentTimeMillis();
		return new java.sql.Timestamp(millis);
	}

	default double distanceBetweenEventAndUser(Event event, User user) {
		double lat2 = user.getUserLatitude();
		double lat1 = event.getLatitude();
		double lon2 = user.getUserLongitude();
		double lon1 = event.getLongitude();
		return calculateDistance(lat2, lat1, lon2, lon1);
	}

	default double calculateDistance(double lat2, double lat1, double lon2, double lon1) {
		int radius = 6371;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return radius * c;
	}
}
