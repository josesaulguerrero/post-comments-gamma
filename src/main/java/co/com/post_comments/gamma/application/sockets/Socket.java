package co.com.post_comments.gamma.application.sockets;

import co.com.post_comments.gamma.application.bus.views.CommentView;
import co.com.post_comments.gamma.application.bus.views.PostView;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class Socket {
    private SimpMessagingTemplate template;

    public void emitPostCreated(PostView view) {
        this.template.convertAndSend("/topic/post.created", view);
    }

    public void emitCommentAdded(CommentView view) {
        this.template.convertAndSend(
                String.format("/topic/%s/comment.added", view.getPostId()),
                view
        );
    }
}