package com.technicalsand.events.sse;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Controller
@RequestMapping("/sse")
@AllArgsConstructor
public class SseContoller {
	private final SseService sseService;

	@GetMapping("/client")
	public String getCient() {
		return "sseClient";
	}

	@GetMapping("/receive")
	public @ResponseBody
	SseEmitter getEmitter() {
		return sseService.registerClient();
	}

	@GetMapping("/message")
	public @ResponseBody void sendMessages(@RequestParam String message,
					  @RequestParam(required = false) String user) throws IOException {
		sseService.process(message, user);
	}

	@Scheduled(cron = "*/5 * * ? * *")
	public void runJob() throws IOException {
		sseService.process("Scheduled job run", "Job");
	}

}
