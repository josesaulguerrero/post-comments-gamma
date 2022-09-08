package co.com.post_comments.gamma.application.sockets;

import co.com.post_comments.gamma.application.bus.views.CommentView;
import co.com.post_comments.gamma.application.bus.views.PostView;
import co.com.post_comments.gamma.application.commons.json.JSONMapper;
import co.com.post_comments.gamma.application.commons.json.JSONMapperImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@NoArgsConstructor
@ServerEndpoint("/retrieve/{correlationId}")
public class MainSocket {
    private static final Map<String, Map<String, Session>> sessions = new ConcurrentHashMap<>();
    private final JSONMapper jsonMapper = new JSONMapperImpl();

    @OnOpen
    public void onOpen(Session session, @PathParam("correlationId") String correlationId) {
        Map<String, Session> map = sessions.getOrDefault(correlationId, new HashMap<>());
        map.put(session.getId(), session);
        sessions.put(correlationId, map);
        log.info("connected from " + correlationId);
    }

    @OnClose
    public void onClose(Session session, @PathParam("correlationId") String correlationId) {
        sessions.get(correlationId).remove(session.getId());
        log.info("disconnected from " + correlationId);
    }

    @OnError
    public void onError(Session session, @PathParam("correlationId") String correlationId, Throwable throwable) {
        sessions.get(correlationId).remove(session.getId());
        log.error(throwable.getMessage());
        log.info("error at " + correlationId);
    }

    public void sendPostCreated(String correlationId, PostView model) {
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            sessions.get(correlationId).values()
                    .forEach(session -> {
                        try {
                            String message = this.jsonMapper.writeToJson(model);
                            session.getAsyncRemote().sendText(message);
                        } catch (RuntimeException e) {
                            log.error(e.getMessage(), e);
                        }
                    });
        }
    }

    public void sendCommentAdded(String correlationId, CommentView model) {
        log.info(sessions.toString());
        log.info(correlationId);
        log.info(String.valueOf(sessions.containsKey(correlationId)));
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            sessions.get(correlationId).values()
                    .forEach(session -> {
                        try {
                            log.info("hisdahfñklfjasfd 2");
                            String message = this.jsonMapper.writeToJson(model);
                            session.getAsyncRemote().sendText(message);
                        } catch (RuntimeException e) {
                            log.error(e.getMessage(), e);
                        }
                    });
        }
    }
}