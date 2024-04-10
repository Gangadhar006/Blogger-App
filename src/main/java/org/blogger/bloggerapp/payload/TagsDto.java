package org.blogger.bloggerapp.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import static org.blogger.bloggerapp.constants.ValidationConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagsDto {
    @NotBlank(message = TAG_NOT_BLANK_MESSAGE)
    @Length(min = TAG_MIN_LENGTH, max = TAG_MAX_LENGTH, message = TAG_LENGTH_MESSAGE)
    private String tags;
}