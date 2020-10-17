package com.technicalsand.events.responsebodyemitter;

import com.technicalsand.events.sse.SseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/rbe")
@AllArgsConstructor
public class RbeContoller {

	private final RbeService rbeService;

	@GetMapping(value = "/receive")
	public ResponseEntity<ResponseBodyEmitter> getEmitter(){
		ResponseBodyEmitter emitter = rbeService.events();
		return new ResponseEntity(emitter, HttpStatus.OK);
	}

}
