package org.blogger.bloggerapp.serviceImpl;

import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Users;
import org.blogger.bloggerapp.payload.request.BlogPostsRequestDto;
import org.blogger.bloggerapp.payload.response.BlogPostsResponseDto;
import org.blogger.bloggerapp.repository.IBlogPostsRepository;
import org.blogger.bloggerapp.utility.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BlogServiceImplTest {

    @Mock
    private IBlogPostsRepository blogRepo;

    @Mock
    private AppUtils appUtils;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private BlogServiceImpl blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create blog service success test")
    void createBlog() {
        // Prepare data
        Users user = new Users();
        user.setId(1L);

        BlogPostsRequestDto blogRequest = BlogPostsRequestDto.builder()
                .title("title")
                .content("content")
                .imageData(new byte[]{10, 20, 30})
                .user(user)
                .build();

        BlogPosts blogPosts = new BlogPosts();
        blogPosts.setTitle(blogRequest.getTitle());
        blogPosts.setContent(blogRequest.getContent());
        blogPosts.setImageData(blogRequest.getImageData());
        blogPosts.setUser(user);

        BlogPostsResponseDto blogResponse = BlogPostsResponseDto.builder()
                .id(1L)
                .title(blogRequest.getTitle())
                .content(blogRequest.getContent())
                .imageData(blogRequest.getImageData())
                .createdAt(LocalDateTime.now())
                .build();

        // Mocking behavior
        when(mapper.map(blogRequest, BlogPosts.class)).thenReturn(blogPosts);
        when(appUtils.currentLoginUser()).thenReturn(user);
        when(blogRepo.save(blogPosts)).thenReturn(blogPosts);
        when(mapper.map(blogPosts, BlogPostsResponseDto.class)).thenReturn(blogResponse);

        // Invoke the method under test
        BlogPostsResponseDto actualResponse = blogService.createBlog(blogRequest);

        // Assertions
        assertEquals(blogResponse.getId(), actualResponse.getId());
        assertEquals(blogResponse.getTitle(), actualResponse.getTitle());
        assertEquals(blogResponse.getContent(), actualResponse.getContent());
        assertEquals(blogResponse.getImageData(), actualResponse.getImageData());

        // Verify method invocations
        verify(blogRepo, times(1)).save(blogPosts);
        verify(appUtils, times(1)).currentLoginUser();
        verify(mapper, times(1)).map(blogRequest, BlogPosts.class);
        verify(mapper, times(1)).map(blogPosts, BlogPostsResponseDto.class);
    }
}


/** notes:
 *  to convert above test as an integration test, make following changes
 *      annotate this with @SpringBootTest
 *      use @MockBean instead of @Mock to mock dependencies within the spring context
 *      use @Autowired instead of @InjectBeans
 *      while preforming integration test, it is better to configure test db (because we dont want to mess up with prod db)
 *      for solely testing repository layer we can use @DataJpaTest
 */
