package org.blogger.bloggerapp.service;

import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ISearchService {
    List<BlogPostsResponseDto> searchByTag(String tag);

    List<BlogPostsResponseDto> searchByUser(String username);

    List<BlogPostsResponseDto> searchByDateRange(LocalDate fromDate, LocalDate toDate);
}
