package co.com.post_comments.gamma.application.config;

import co.com.post_comments.gamma.application.bus.views.CommentView;
import co.com.post_comments.gamma.application.bus.views.PostView;
import co.com.post_comments.gamma.application.commons.json.JSONMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {
    public static final String PROXY_QUEUE_POST_CREATED = "events.proxy.post.created";
    public static final String PROXY_QUEUE_COMMENT_ADDED = "events.proxy.comment.added";
    
    private final JSONMapper jsonMapper;

    @RabbitListener(queues = { PROXY_QUEUE_POST_CREATED })
    public void postCreatedRabbitListener(String jsonView) {
        PostView view = (PostView) this.jsonMapper.readFromJson(jsonView, PostView.class);
        System.out.println("post view = " + view);
    }

    @RabbitListener(queues = { PROXY_QUEUE_COMMENT_ADDED })
    public void commendAddedRabbitListener(String jsonView) {
        CommentView view = (CommentView) this.jsonMapper.readFromJson(jsonView, CommentView.class);
        System.out.println("comment view = " + view);
    }
}
