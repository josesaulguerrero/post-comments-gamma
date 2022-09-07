package co.com.post_comments.gamma.domain.post.entities.root;

import co.com.post_comments.gamma.domain.post.entities.Comment;
import co.com.post_comments.gamma.domain.post.events.CommentAdded;
import co.com.post_comments.gamma.domain.post.events.CommentContentChanged;
import co.com.post_comments.gamma.domain.post.events.PostCreated;
import co.com.post_comments.gamma.domain.post.values.Author;
import co.com.post_comments.gamma.domain.post.values.Content;
import co.com.post_comments.gamma.domain.post.values.Date;
import co.com.post_comments.gamma.domain.post.values.Title;
import co.com.post_comments.gamma.domain.post.values.identities.CommentId;
import co.com.post_comments.gamma.domain.post.values.identities.PostId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@ToString
@EqualsAndHashCode(callSuper = true)
public class Post extends AggregateEvent<PostId> {
    String author;
    String title;
    String content;
    String postedAt;
    List<Comment> comments;

    private Post(PostId entityId) {
        super(entityId);
        super.subscribe(new PostEventListener(this));
    }

    public Post(PostId entityId, Author author, Title title, Content content, Date postedAt) {
        super(entityId);
        Objects.requireNonNull(entityId);
        Objects.requireNonNull(author);
        Objects.requireNonNull(title);
        Objects.requireNonNull(content);
        Objects.requireNonNull(postedAt);
        super
                .appendChange(new PostCreated(entityId.value(), title.value(), content.value(), author.value(), postedAt.value().toString()))
                .apply();
    }

    public static Post from(PostId postId, List<DomainEvent> events) {
        Objects.requireNonNull(postId);
        Objects.requireNonNull(events);
        Post post = new Post(postId);
        events.forEach(post::applyEvent);
        return post;
    }

    public void addComment(PostId postId, CommentId commentId, Author commentAuthor, Content commentContent, Date postedAt) {
        Objects.requireNonNull(postId);
        Objects.requireNonNull(commentId);
        Objects.requireNonNull(commentAuthor);
        Objects.requireNonNull(commentContent);
        Objects.requireNonNull(postedAt);
        super
                .appendChange(new CommentAdded(postId.value(), commentId.value(), commentAuthor.value(), commentContent.value(), postedAt.value().toString()))
                .apply();
    }

    public void changeCommentContent(PostId postId, CommentId commentId, Content commentContent) {
        Objects.requireNonNull(postId);
        Objects.requireNonNull(commentId);
        Objects.requireNonNull(commentContent);
        super
                .appendChange(new CommentContentChanged(postId.value(), commentId.value(), commentContent.value()))
                .apply();
    }

    public String author() {
        return author;
    }

    public String title() {
        return title;
    }

    public String postedAt() {
        return postedAt;
    }

    public List<Comment> comments() {
        return comments;
    }
}
