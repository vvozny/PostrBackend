package com.example.postrbackend.services;

import com.example.postrbackend.data.entities.PushNotificationRequest;
import com.google.firebase.messaging.*;

import java.util.concurrent.ExecutionException;

public interface FCMService {

	void sendMessageToToken(PushNotificationRequest request) throws InterruptedException, ExecutionException;

	String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException;

	AndroidConfig getAndroidConfig(String topic);

	ApnsConfig getApnsConfig(String topic) ;

	Message getPreconfiguredMessageToToken(PushNotificationRequest request);

	Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request);


}
