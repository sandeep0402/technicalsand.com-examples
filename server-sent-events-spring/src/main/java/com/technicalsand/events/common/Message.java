package com.technicalsand.events.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {

	private String content;
	private String toUser;
	private String fromUser;
	private LocalDateTime localDateTime;
}
