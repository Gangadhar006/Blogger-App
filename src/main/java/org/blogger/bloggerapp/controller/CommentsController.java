package org.blogger.bloggerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.payload.CommentsDto;
import org.blogger.bloggerapp.service.ICommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.blogger.bloggerapp.constants.ApiEndpointConstants.COMMENTS_BASE_URL;

@RestController
@RequestMapping(COMMENTS_BASE_URL)
@RequiredArgsConstructor
public class CommentsController {

    private final ICommentsService commentService;

    @PostMapping
    public ResponseEntity<CommentsDto> postComment(@Valid @RequestBody CommentsDto commentsDto,
                                                   @PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.postComment(commentsDto, blogId));
    }

    @GetMapping
    public ResponseEntity<List<CommentsDto>> getComments(@PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments(blogId));
    }
}
