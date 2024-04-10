package org.blogger.bloggerapp.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import static org.blogger.bloggerapp.constants.ValidationConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank(message = USERNAME_MESSAGE)
    @Length(min = USERNAME_LENGTH,message = USERNAME_PATTERN_MESSAGE)
    private String username;

    @Pattern(regexp = EMAIL_PATTERN, message = EMAIL_PATTERN_MESSAGE)
    @NotBlank(message = EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = PASSWORD_MESSAGE)
    @Length(min = PASSWORD_LENGTH, message = PASSWORD_PATTERN_MESSAGE)
    private String password;
}