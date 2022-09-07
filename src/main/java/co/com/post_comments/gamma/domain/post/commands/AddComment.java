package co.com.post_comments.gamma.domain.post.commands;

import co.com.sofka.domain.generic.Command;
import lombok.*;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddComment extends Command {
    private String postId;
    private String author;
    private String content;
    private String postedAt;

    public String postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public String postedAt() {
        return postedAt;
    }
}
