package co.com.post_comments.gamma.application.bus.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    private String id;
    private String postId;
    private String author;
    private String content;
    private LocalDateTime postedAt;

    public String id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String postId() {
        return postId;
    }

    public String content() {
        return content;
    }

    public LocalDateTime postedAt() {
        return postedAt;
    }
}
