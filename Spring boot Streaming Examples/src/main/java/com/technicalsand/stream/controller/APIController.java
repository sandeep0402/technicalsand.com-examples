package com.technicalsand.stream.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicalsand.stream.pojo.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.time.Duration;

@RequestMapping("/api/stream")
@RestController
public class APIController {

	@GetMapping("/content")
	public ResponseEntity<StreamingResponseBody> streamContent () {
		StreamingResponseBody responseBody = response -> {
			for (int i = 1; i <= 1000; i++) {
				response.write(("Data stream line - " + i +"\n").getBytes());
				response.flush();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		//return new ResponseEntity(responseBody, HttpStatus.OK);
		return ResponseEntity.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(responseBody);
	}

	@GetMapping("/textfile")
	public ResponseEntity<StreamingResponseBody> streamContentAsFile () {
		StreamingResponseBody responseBody = response -> {
			for (int i = 1; i <= 1000; i++) {
				response.write(("Data stream line - " + i +"\n").getBytes());
				response.flush();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		//return new ResponseEntity(responseBody, HttpStatus.OK);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test_data.txt")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(responseBody);
	}

	@GetMapping("/staticfile")
	public ResponseEntity<StreamingResponseBody> streamPdfFile () throws FileNotFoundException {
		String fileName = "Technicalsand.com sample data.pdf";
		File file = ResourceUtils.getFile("classpath:static/"+fileName);
		StreamingResponseBody responseBody = outputStream -> {
			Files.copy(file.toPath(), outputStream);
		};
		//return new ResponseEntity(responseBody, HttpStatus.OK);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Downloaded_"+fileName)
				.contentType(MediaType.APPLICATION_PDF)
				.body(responseBody);
	}

	@GetMapping("/json")
	public ResponseEntity<StreamingResponseBody> streamJson () {
		int maxRecords = 1000;
		StreamingResponseBody responseBody = response -> {
			response.write("{\"students\":[".getBytes());
			response.flush();
			for (int i = 1; i <= maxRecords; i++) {
				Student st = new Student("Name" + i, i);
				//response.flush();
				//Creating the ObjectMapper object
				ObjectMapper mapper = new ObjectMapper();
				//Converting the Object to JSONString
				String jsonString = mapper.writeValueAsString(st);
				if( i < maxRecords){
					jsonString += ",";
				}
				response.write(jsonString.getBytes());
				response.flush();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			response.write("]}".getBytes());
			response.flush();

		};
		//return new ResponseEntity(responseBody, HttpStatus.OK);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(responseBody);
	}

	@GetMapping(value = "/json/objects", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Student> streamJsonObjects() {
		return Flux.interval(Duration.ofSeconds(1)).map(i -> new Student("Name" + i, i.intValue()));
	}
}
