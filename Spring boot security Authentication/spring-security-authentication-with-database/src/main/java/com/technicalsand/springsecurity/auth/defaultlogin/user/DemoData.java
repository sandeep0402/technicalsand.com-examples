package com.technicalsand.springsecurity.auth.defaultlogin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class DemoData {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		if(roleRepository.count() ==0 ) {
			System.out.println("Saving demo data");
			Role roleAdmin = new Role("ADMIN");
			Role roleUser = new Role("USER");
			roleRepository.save(roleAdmin);
			roleRepository.save(roleUser);

			User userAdmin = new User("technicalsand", passwordEncoder.encode("technicalsand"), roleAdmin);
			User userReader = new User("reader", passwordEncoder.encode("reader1"), roleUser);

			userRepository.save(userAdmin);
			userRepository.save(userReader);

			System.out.println("Demo data saved successfully.");

		}
	}
}
