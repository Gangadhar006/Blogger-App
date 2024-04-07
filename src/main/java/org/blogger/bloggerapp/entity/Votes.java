package org.blogger.bloggerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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