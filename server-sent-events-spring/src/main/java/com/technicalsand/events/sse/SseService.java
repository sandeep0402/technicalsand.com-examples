package com.technicalsand.events.sse;

import com.technicalsand.events.common.Notification;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Data
public class SseService {

	// Save all emitters from different connections, to send message to all
	private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	public SseEmitter registerClient() {
		SseEmitter emitter = new SseEmitter();

		// Add client specific new emitter in global list
		emitters.add(emitter);

		// On Client connection completion, unregister client specific emitter
		emitter.onCompletion(() -> emitters.remove(emitter));

		// On Client connection timeout, unregister and mark complete client specific emitter
		emitter.onTimeout(() -> {
			emitter.complete();
			emitters.remove(emitter);
		});
		return emitter;
	}

	public void process(String message, String user) throws IOException {
		Notification notification = Notification.builder()
				.user(StringUtils.isBlank(user) ? "Guest" : user)
				.message(message)
				.build();

		sendEventToClients(notification);
	}

	public void sendEventToClients(Notification notification) {
		// Track which events could not be sent
		List<SseEmitter> deadEmitters = new ArrayList<>();
		// Send to all registered clients
		emitters.forEach(emitter -> {
			try {
				emitter.send(notification);
			} catch (Exception e) {
				// record failed ones
				deadEmitters.add(emitter);
			}
		});
		// remove the failed one, otherwise it will keep on waiting for client connection
		emitters.remove(deadEmitters);
	}
}
