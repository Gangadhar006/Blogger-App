package org.blogger.bloggerapp.controller;

import lombok.RequiredArgsConstructor;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.service.ISearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.blogger.bloggerapp.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(SEARCH_BASE_URL)
@RequiredArgsConstructor
public class SearchController {
    private final ISearchService searchService;

    @GetMapping
    public ResponseEntity<List<BlogPostsResponseDto>> searchBLogs(@RequestParam(required = false) String tag,
                                                                  @RequestParam(required = false) String username,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate toDate) {
        if (tag != null)
            return ResponseEntity.status(HttpStatus.OK).body(searchService.searchByTag(tag));
        else if (username != null)
            return ResponseEntity.status(HttpStatus.OK).body(searchService.searchByUser(username));
        else if (fromDate != null && toDate != null)
            return ResponseEntity.status(HttpStatus.OK).body(searchService.searchByDateRange(fromDate, toDate));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}