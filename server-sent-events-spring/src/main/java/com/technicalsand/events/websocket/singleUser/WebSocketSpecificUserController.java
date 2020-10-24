package com.technicalsand.events.websocket.singleUser;

import com.technicalsand.events.common.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

@Controller
@Slf4j
@AllArgsConstructor
public class WebSocketSpecificUserController {

	private SessionsStorage sessionsStorage;
	private SimpMessagingTemplate template;

	@GetMapping("websocket/user")
	public String getClient(ModelMap map){
		map.addAttribute("defaultUser", sessionsStorage.getRandomUserName());
		return "websocketClientSpecificUser";
	}

	@GetMapping("websocket/users")
	public @ResponseBody ResponseEntity<SortedSet<String>> getOnlineUsers(){
		SortedSet<String> users = new TreeSet(sessionsStorage.getAllSessionIds().keySet());
		return ResponseEntity.ok()
				.body(users);
	}

	@MessageMapping("/websocket/message")
	public void message(Message message) {
		String sessionId=sessionsStorage.getSessionId(message.getToUser());
		message.setLocalDateTime(LocalDateTime.now());
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		headerAccessor.setSessionId(sessionId);
		headerAccessor.setLeaveMutable(true);
		template.convertAndSendToUser(sessionId,"/topic/message", message ,headerAccessor.getMessageHeaders());
	}

}
