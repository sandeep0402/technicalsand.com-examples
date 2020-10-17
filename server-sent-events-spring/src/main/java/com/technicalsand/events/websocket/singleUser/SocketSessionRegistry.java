package com.technicalsand.events.websocket.singleUser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class SocketSessionRegistry {

	//Save user sessions in a map as username, sessionid
	private final ConcurrentMap<String, String> sessionIds = new ConcurrentHashMap();
	private final Random random = new Random();

	public SocketSessionRegistry() {
	}

	public String getSessionId(String user) {
		return sessionIds.get(user);
	}

	public ConcurrentMap<String, String> getAllSessionIds() {
		return sessionIds;
	}

	public void registerSessionId(String user, String sessionId) {
		Assert.notNull(user, "User must not be null");
		Assert.notNull(sessionId, "Session ID must not be null");
		sessionIds.put(user, sessionId);
	}

	public void unregisterSessionId(String sessionId) {
		Assert.notNull(sessionId, "Session ID must not be null");
		sessionIds.entrySet().removeIf(entry -> StringUtils.equals(sessionId, entry.getValue()));
	}

	public String getRandomUserName() {
		String userName = "user-" + random.nextInt(100);
		if (!sessionIds.containsKey(userName)) {
			return userName;
		}
		return getRandomUserName();
	}
}