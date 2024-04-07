package org.blogger.bloggerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BlogPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users user;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comments> comments;

    @ManyToMany(mappedBy = "post")
    private List<Tags> tags;

    @OneToMany(mappedBy = "post")
    private List<Votes> votes;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
