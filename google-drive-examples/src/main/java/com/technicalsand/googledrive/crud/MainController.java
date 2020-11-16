package com.technicalsand.googledrive.crud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@AllArgsConstructor
public class MainController {
	private GoogleDriveManager driveQuickstart;

	@GetMapping("/")
	public void print() throws IOException, GeneralSecurityException {
		driveQuickstart.printFileNames();
	}

	@GetMapping("/download/{id}")
	public void download(@PathVariable String id, HttpServletResponse response) throws IOException, GeneralSecurityException {
		driveQuickstart.downloadFile(id, response.getOutputStream());
	}
}
