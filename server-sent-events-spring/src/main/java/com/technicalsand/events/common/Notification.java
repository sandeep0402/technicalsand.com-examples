package com.technicalsand.events.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Notification {

	private String user;
	private String message;
	@Builder.Default
	private LocalDateTime localDateTime = LocalDateTime.now();
}