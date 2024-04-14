package org.blogger.bloggerapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.service.IBlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class BlogPostsControllerTest {
    @Mock
    private IBlogService blogService;

    @InjectMocks
    private BlogPostsController blogControlelr;

    private MockMvc mockMvc;

    private BlogPostsRequestDto blogRequest;
    private BlogPostsResponseDto blogResponse;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // use mockMvc for integration tests
        mockMvc = MockMvcBuilders.standaloneSetup(blogControlelr).build();
        objectMapper = new ObjectMapper();
        Users user = new Users();
        user.setId(1L);
        blogRequest = BlogPostsRequestDto.builder()
                .title("title")
                .content("Integration with Spring Boot: If you are using Spring Boot, it automatically configures ObjectMapper beans. You can inject ObjectMapper into your test class via @Autowired or retrieve it from the Spring context.")
                .imageData(new byte[]{10, 20, 30})
                .user(user)
                .build();
        blogResponse = BlogPostsResponseDto.builder()
                .id(1L)
                .title(blogRequest.getTitle())
                .content(blogRequest.getContent())
                .imageData(blogRequest.getImageData())
                .build();
    }

    @Test
    @DisplayName("create blog valid request test")
    void createBlog() throws Exception {

        // mocking behaviour
        when(blogService.createBlog(blogRequest)).thenReturn(blogResponse);

        // performing actual test
        ResponseEntity<BlogPostsResponseDto> response = blogControlelr.createBlog(blogRequest);

        // verify the response
        // for unit testing use assertEquals
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(blogResponse.getTitle(), response.getBody().getTitle());
        assertEquals(blogResponse.getContent(), response.getBody().getContent());
        assertEquals(blogResponse.getImageData(), response.getBody().getImageData());
        assertEquals(blogResponse.getId(), response.getBody().getId());
        assertEquals(blogResponse, response.getBody());

        // for integration testing use MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/api/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(blogRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("string"))
                .andExpect(jsonPath("$.content").value("string"))
                .andExpect(jsonPath("$.createdAt").isNotEmpty());

        // verify that service method is called
        verify(blogService, times(1)).createBlog(blogRequest);
    }
}