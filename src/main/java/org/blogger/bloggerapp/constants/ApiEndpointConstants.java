package org.blogger.bloggerapp.constants;

public class ApiEndpointConstants {

    // AuthenticationController Endpoints
    public static final String AUTHENTICATION_BASE_URL = "/api/user";
    public static final String AUTHENTICATION_SIGNIN_URL = "/signin";
    public static final String AUTHENTICATION_SIGNUP_URL = "/signup";


    // BlogPostsController Endpoints
    public static final String BLOGPOSTS_BASE_URL = "/api/blog";
    public static final String BLOGPOSTS_ID_URL = "{blogId}";
    public static final String BLOGPOSTS_IMAGE_URL = "{blogId}/image";
    public static final String BLOGPOSTS_TAGS_URL = "{blogId}/tags";


    // CommentsController Endpoints
    public static final String COMMENTS_BASE_URL = "/api/{blogId}/comment";


    // VotesController Endpoints
    public static final String VOTES_BASE_URL = "/api/{blogId}/votes";
    public static final String VOTES_UPVOTE_URL = "/upvote";
    public static final String VOTES_DOWNVOTE_URL = "/downvote";


    // SearchController Endpoints
    public static final String SEARCH_BASE_URL = "/api/blog/search";
}