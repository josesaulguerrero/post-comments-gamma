package co.com.post_comments.gamma.application.bus;

import co.com.post_comments.gamma.application.bus.views.CommentView;
import co.com.post_comments.gamma.application.bus.views.PostView;
import co.com.post_comments.gamma.application.commons.json.JSONMapper;
import co.com.post_comments.gamma.application.sockets.Socket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQConsumer {
    public static final String PROXY_QUEUE_POST_CREATED = "events.proxy.post.created";
    public static final String PROXY_QUEUE_COMMENT_ADDED = "events.proxy.comment.added";
    private final JSONMapper jsonMapper;
    private final Socket socket;

    @RabbitListener(queues = {PROXY_QUEUE_POST_CREATED})
    public void postCreatedRabbitListener(String jsonView) {
        log.info("Received a PostView with the following information " + jsonView + " from the BETA queue.");
        PostView view = (PostView) this.jsonMapper.readFromJson(jsonView, PostView.class);
        this.socket.emitPostCreated(view);
    }

    @RabbitListener(queues = {PROXY_QUEUE_COMMENT_ADDED})
    public void commendAddedRabbitListener(String jsonView) {
        log.info("Received a CommentView with the following information " + jsonView + " from the BETA queue.");
        CommentView view = (CommentView) this.jsonMapper.readFromJson(jsonView, CommentView.class);
        this.socket.emitCommentAdded(view);
    }
}
