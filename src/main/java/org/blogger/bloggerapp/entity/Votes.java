package org.blogger.bloggerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long upVote;
    private Long downVote;

    @ManyToOne
    private Users user;

    @ManyToOne
    private BlogPosts post;
}