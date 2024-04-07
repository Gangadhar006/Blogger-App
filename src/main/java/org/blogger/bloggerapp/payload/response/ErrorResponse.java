package org.blogger.bloggerapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatusCode statusCode;
}
