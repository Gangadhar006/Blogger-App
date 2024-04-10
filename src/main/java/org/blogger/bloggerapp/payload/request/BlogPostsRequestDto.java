package org.blogger.bloggerapp.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.blogger.bloggerapp.entity.Users;
import org.hibernate.validator.constraints.Length;

import static org.blogger.bloggerapp.constants.ValidationConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostsRequestDto {
    @NotBlank(message = TITLE_NOT_BLANK_MESSAGE)
    @Length(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH, message = TITLE_LENGTH_MESSAGE)
    private String title;

    @NotBlank(message = CONTENT_NOT_BLANK_MESSAGE)
    @Length(min = CONTENT_MIN_LENGTH, max = CONTENT_MAX_LENGTH, message = CONTENT_LENGTH_MESSAGE)
    private String content;

    private byte[] imageData;

    @JsonIgnore
    private Users user;
}