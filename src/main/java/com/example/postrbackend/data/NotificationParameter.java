package com.example.postrbackend.data;

public enum NotificationParameter {
	SOUND("default"),
	COLOR("#6495ED");

	private String value;

	NotificationParameter(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
