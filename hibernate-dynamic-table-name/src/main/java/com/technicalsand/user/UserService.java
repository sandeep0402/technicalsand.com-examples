package com.technicalsand.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EntityManager em;

	public List<User> getUsers(String tableName){
		logger.info("Data requested from table: " + tableName);
		//return userRepository.findAll();
		return getAllAttendance();
	}

	public List<User> getAllAttendance() {
		Query query = em.createQuery("FROM User");
		List<User> attendanceList = query.getResultList();
		return attendanceList;
	}
}
