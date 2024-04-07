package org.blogger.bloggerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Votes> votes;

    @OneToMany(mappedBy = "user")
    private List<BlogPosts> blogPosts;

    @CreatedDate
    private LocalDateTime createdAt;
}
