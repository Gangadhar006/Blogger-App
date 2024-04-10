package org.blogger.bloggerapp.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import static org.blogger.bloggerapp.constants.ValidationConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    @NotBlank(message = COMMENT_NOT_BLANK_MESSAGE)
    @Length(min = COMMENT_MIN_LENGTH, max = COMMENT_MAX_LENGTH, message = COMMENT_LENGTH_MESSAGE)
    private String comment;
}
