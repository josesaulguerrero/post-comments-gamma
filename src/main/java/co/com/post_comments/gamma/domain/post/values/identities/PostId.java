package co.com.post_comments.gamma.domain.post.values.identities;

import co.com.sofka.domain.generic.Identity;

public class PostId extends Identity {
    public PostId() {
        super();
    }

    public PostId(String uuid) {
        super(uuid);
    }
}
