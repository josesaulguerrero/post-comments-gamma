package co.com.post_comments.gamma.application.sockets;

import co.com.post_comments.gamma.application.bus.views.CommentView;
import co.com.post_comments.gamma.application.bus.views.PostView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
public class Socket {
    private SimpMessagingTemplate template;

    public void emitPostCreated(PostView view) {
        log.info("Emitting a PostView to the post.created socket topic.");
        this.template.convertAndSend("/topic/post.created", view);
    }

    public void emitCommentAdded(CommentView view) {
        log.info("Emitting a CommentView to the comment.added socket topic.");
        this.template.convertAndSend(
                String.format("/topic/%s/comment.added", view.getPostId()),
                view
        );
    }
}