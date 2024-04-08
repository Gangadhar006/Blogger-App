package org.blogger.bloggerapp.controller;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.service.IVotesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.blogger.bloggerapp.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(VOTES_BASE_URL)
@RequiredArgsConstructor
public class VotesController {

    private final IVotesService votesService;

    @PostMapping(VOTES_UPVOTE_URL)
    public ResponseEntity<Long> upVote(@PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.OK).body(votesService.upVote(blogId));
    }

    @PostMapping(VOTES_DOWNVOTE_URL)
    public ResponseEntity<Long> downVote(@PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.OK).body(votesService.downVote(blogId));
    }
}