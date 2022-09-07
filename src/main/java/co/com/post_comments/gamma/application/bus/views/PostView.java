package co.com.post_comments.gamma.application.bus.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostView {
    private String id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private Set<CommentView> comments;

    public void addComment(CommentView commentView) {
        this.comments.add(commentView);
    }

    public String id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public LocalDateTime postedAt() {
        return postedAt;
    }

    public Set<CommentView> comments() {
        return comments;
    }
}
