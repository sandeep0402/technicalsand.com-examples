package com.technicalsand;

import com.technicalsand.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class ExampleController {

	@GetMapping("/example/constructor")
	public ResponseEntity<String> exampleConstructor() {
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/example/builder")
	public ResponseEntity<String> exampleBuilder() {
		return ResponseEntity.ok("Builder example");
	}

	@GetMapping("/example/headers")
	public ResponseEntity exampleHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("technicalsand-Example-Header", "https://technicalsand.com");
		return ResponseEntity.ok().headers(responseHeaders).build();
	}

	@GetMapping("/example/empty")
	public ResponseEntity empty() {
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/example/optional/empty")
	public ResponseEntity<List<String>> emptyOptional(@RequestParam(required = false) boolean fillData) {
		if (fillData) {
			List<String> data = Collections.singletonList("Sample Data");
			return ResponseEntity.ok(data);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/example/string")
	public ResponseEntity<String> exampleString() {
		return ResponseEntity.ok("String example here");
	}

	@GetMapping("/example/json")
	public ResponseEntity<Student> exampleJson() {
		Student student = Student.builder().rollNo(10).name("Student1").className("first").build();
		return ResponseEntity.ok(student);
	}

	@GetMapping("/example/list")
	public ResponseEntity<List<Student>> exampleList() {
		Student student1 = Student.builder().rollNo(10).name("Student1").className("first").build();
		Student student2 = Student.builder().rollNo(11).name("Student2").className("first").build();
		List<Student> students = List.of(student1, student2);
		return ResponseEntity.ok(students);
	}

	@GetMapping("/example/status")
	public ResponseEntity exampleOnlyStatus() {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}

	@GetMapping("/example/stream")
	public ResponseEntity<StreamingResponseBody> exampleStream() {
		StreamingResponseBody streamingResponseBody = outputStream -> {
			for (int i = 1; i <= 100000; i++) {
				outputStream.write(("Data stream line - " + i + "\n").getBytes());
			}
		};
		return ResponseEntity.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(streamingResponseBody);
	}

	@GetMapping("/example/csv")
	public ResponseEntity<StreamingResponseBody> exampleCsv() {
		StreamingResponseBody stream = output -> {
			Writer writer = new BufferedWriter(new OutputStreamWriter(output));
			writer.write("name,rollNo,class" + "\n");
			for (int i = 1; i <= 10000; i++) {
				Student st = Student.builder().rollNo(i).name("Student" + i).className("second").build();
				writer.write(st.getName() + "," + st.getRollNo() + "," + st.getClassName() + "\n");
				writer.flush();
			}
		};
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.csv")
				.contentType(MediaType.TEXT_PLAIN)
				.body(stream);
	}

	@PostMapping("/example/request")
	public ResponseEntity<Student> requestEntityExample(RequestEntity<Student> requestEntity) {
		log.info("RequestEntity Headers {}", requestEntity.getHeaders());
		log.info("RequestEntity method {}", requestEntity.getMethod());
		log.info("RequestEntity URL {}", requestEntity.getUrl());
		log.info("RequestEntity Type {}", requestEntity.getType());
		log.info("RequestEntity body {}", requestEntity.getBody());

		return ResponseEntity.ok(requestEntity.getBody());
	}


}
