package co.com.post_comments.gamma.domain.post.values.identities;

import co.com.sofka.domain.generic.Identity;

public class CommentId extends Identity {
    public CommentId() {
        super();
    }

    public CommentId(String uuid) {
        super(uuid);
    }
}
