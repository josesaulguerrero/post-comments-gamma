package co.com.post_comments.gamma.domain.post.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Content implements ValueObject<String> {
    private final String value;

    @Override
    public String value() {
        return this.value;
    }
}
