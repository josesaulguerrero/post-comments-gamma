package co.com.post_comments.gamma.application.bus;

import co.com.post_comments.gamma.application.bus.views.CommentView;
import co.com.post_comments.gamma.application.bus.views.PostView;
import co.com.post_comments.gamma.application.commons.json.JSONMapper;
import co.com.post_comments.gamma.application.sockets.MainSocket;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQConsumer {
    public static final String PROXY_QUEUE_POST_CREATED = "events.proxy.post.created";
    public static final String PROXY_QUEUE_COMMENT_ADDED = "events.proxy.comment.added";
    private final JSONMapper jsonMapper;
    private final MainSocket socket;

    @RabbitListener(queues = {PROXY_QUEUE_POST_CREATED})
    public void postCreatedRabbitListener(String jsonView) {
        PostView view = (PostView) this.jsonMapper.readFromJson(jsonView, PostView.class);
        this.socket.sendPostCreated("main space", view);
    }

    @RabbitListener(queues = {PROXY_QUEUE_COMMENT_ADDED})
    public void commendAddedRabbitListener(String jsonView) {
        CommentView view = (CommentView) this.jsonMapper.readFromJson(jsonView, CommentView.class);
        this.socket.sendCommentAdded(view.id(), view);
    }
}
