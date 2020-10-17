package com.technicalsand.events.responsebodyemitter;

import com.technicalsand.events.common.Notification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Data
@Slf4j
public class RbeService {

	public ResponseBodyEmitter events() {
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
			try {
				String message = "Only text message can be sent";
				for(int i=0; i<5; i++) {
					emitter.send(message + " "+ (i+1) , MediaType.TEXT_PLAIN);
					Thread.sleep(1000);
				}
				emitter.complete();
			} catch (Exception ex) {
				emitter.completeWithError(ex);
			}
		return emitter;
	}
}
