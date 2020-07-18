package com.example.postrbackend.services;

import com.example.postrbackend.data.entities.PushNotificationRequest;

public interface PushNotificationService {
	public void sendPushNotificationToToken(PushNotificationRequest request);
}
