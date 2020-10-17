package com.technicalsand.events.websocket.broadcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class WebSocketController {

	@MessageMapping("/news")
	@SendTo("/topic/news")
	public @ResponseBody String broadcastNews(@Payload String message) {
		return message;
	}

	@GetMapping("websocket/client")
	public String getClient(){
		return "websocketClient";
	}

	
}
