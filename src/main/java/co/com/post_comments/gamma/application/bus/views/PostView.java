package co.com.post_comments.gamma.application.bus.views;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostView {
    private String id;
    private String author;
    private String title;
    private String content;
    private String postedAt;
    private Set<CommentView> comments;

    public void addComment(CommentView commentView) {
        this.comments.add(commentView);
    }
}
