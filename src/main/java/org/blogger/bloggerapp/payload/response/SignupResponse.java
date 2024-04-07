package org.blogger.bloggerapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SignupResponse {
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
