package org.blogger.bloggerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users user;

    @ManyToOne
    private BlogPosts post;

    private String comment;

    @CreatedDate
    private LocalDateTime createdAt;

    private Long parentCommentId;
}
