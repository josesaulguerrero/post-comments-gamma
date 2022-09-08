package co.com.post_comments.gamma.application.bus.views;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    private String id;
    private String postId;
    private String author;
    private String content;
    private String postedAt;
}
