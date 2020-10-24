package com.technicalsand.events.websocket.singleUser;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@NoArgsConstructor
public class SessionsStorage {

	//Save user sessions in a map as username, sessionid
	private final ConcurrentMap<String, String> userSessionsMap = new ConcurrentHashMap();
	private final Random random = new Random();

	// get session id from username
	public String getSessionId(String user) {
		return userSessionsMap.get(user);
	}

	// get details of all online users, whoever is connected has entry in map
	public ConcurrentMap<String, String> getAllSessionIds() {
		return userSessionsMap;
	}

	// Add new user entry with username and session id
	public void registerSessionId(String user, String sessionId) {
		Assert.notNull(user, "User must not be null");
		Assert.notNull(sessionId, "Session ID must not be null");
		userSessionsMap.put(user, sessionId);
	}

	// Once client connection is disconnected, we receive session id which is disconnected
	// find the entry in map from value set and remove it
	public void unregisterSessionId(String sessionId) {
		Assert.notNull(sessionId, "Session ID must not be null");
		userSessionsMap.entrySet().removeIf(entry -> StringUtils.equals(sessionId, entry.getValue()));
	}

	// One utility method to suggest random username
	public String getRandomUserName() {
		String userName = "user-" + random.nextInt(100);
		if (!userSessionsMap.containsKey(userName)) {
			return userName;
		}
		return getRandomUserName();
	}
}