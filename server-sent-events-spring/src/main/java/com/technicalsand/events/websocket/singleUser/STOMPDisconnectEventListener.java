package com.technicalsand.events.websocket.singleUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
public class STOMPDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private SocketSessionRegistry webAgentSessionRegistry;

    @Override
    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //disconnect by sessionid
        String sessionId = sha.getSessionId();
        log.info("Disconnect sessionId: " + sessionId);
        webAgentSessionRegistry.unregisterSessionId(sessionId);
    }
}