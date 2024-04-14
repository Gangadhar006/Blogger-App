package org.blogger.bloggerapp.repository;

import org.blogger.bloggerapp.entity.BlogPosts;
import org.blogger.bloggerapp.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IBlogPostsRepositoryTest {
    @Mock
    private IBlogPostsRepository blogPostsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Find Blog Posts by User")
    public void testFindByUser() {
        // Mocking the input data
        Users user = new Users();
        user.setId(1L);

        BlogPosts post1 = new BlogPosts();
        post1.setId(1L);
        post1.setUser(user);

        BlogPosts post2 = new BlogPosts();
        post2.setId(2L);
        post2.setUser(user);

        List<BlogPosts> expectedPosts = new ArrayList<>();
        expectedPosts.add(post1);
        expectedPosts.add(post2);

        // Mock repository method
        when(blogPostsRepository.findByUser(user)).thenReturn(expectedPosts);

        // Call the repository method
        List<BlogPosts> actualPosts = blogPostsRepository.findByUser(user);

        // Verify the result
        verify(blogPostsRepository, times(1)).findByUser(user);
        assertEquals(expectedPosts.size(), actualPosts.size());
        assertEquals(expectedPosts, actualPosts);
    }
}


/** notes:
 *      either of MockitoAnnotations.openMocks(this) or @ExtendWith(MockitoExtension.class) can be used
 *      to make above test integration use @Autowired and TestEntityManager instance is needed to perform integration test
 *      integration test of above is as follows,
 *
 *         @Autowired
 *         private TestEntityManager entityManager;
 *
 *         @Autowired
 *         private IBlogPostsRepository blogPostsRepository;
 *
 *         public void testFindByUser() {
 *         // Create test data
 *         Users user = new Users();
 *         user.setUsername("testuser");
 *         entityManager.persist(user);
 *
 *         BlogPosts post1 = new BlogPosts();
 *         post1.setTitle("Post 1");
 *         post1.setContent("Content 1");
 *         post1.setUser(user);
 *         entityManager.persist(post1);
 *
 *         BlogPosts post2 = new BlogPosts();
 *         post2.setTitle("Post 2");
 *         post2.setContent("Content 2");
 *         post2.setUser(user);
 *         entityManager.persist(post2);
 *
 *         entityManager.flush();
 *
 *         // Call repository method
 *         List<BlogPosts> postsByUser = blogPostsRepository.findByUser(user);
 *
 *         // Verify the result
 *         assertEquals(2, postsByUser.size());
 *         assertEquals(post1.getId(), postsByUser.get(0).getId());
 *         assertEquals(post2.getId(), postsByUser.get(1).getId());
 *     }
 */