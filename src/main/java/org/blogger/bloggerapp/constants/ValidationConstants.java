package org.blogger.bloggerapp.constants;

public class ValidationConstants {
    // user validation
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String EMAIL_PATTERN_MESSAGE = "Enter valid Email";
    public static final String USERNAME_MESSAGE = "Username should not be empty!";
    public static final String EMAIL_MESSAGE = "Email should not be empty!";
    public static final String PASSWORD_MESSAGE = "Password should not be empty!";
    public static final int PASSWORD_LENGTH = 8;
    public static final int USERNAME_LENGTH = 8;
    public static final String PASSWORD_PATTERN_MESSAGE = "Password must be at least " + PASSWORD_LENGTH + " characters long";
    public static final String USERNAME_PATTERN_MESSAGE = "Password must be at least " + USERNAME_LENGTH + " characters long";


    // blog title validation
    public static final String TITLE_NOT_BLANK_MESSAGE = "Title should not be blank!";
    public static final int TITLE_MIN_LENGTH = 5;
    public static final int TITLE_MAX_LENGTH = 25;
    public static final String TITLE_LENGTH_MESSAGE = "Title should be between " + TITLE_MIN_LENGTH + " and " + TITLE_MAX_LENGTH + " characters";


    // blog content validation
    public static final String CONTENT_NOT_BLANK_MESSAGE = "Content should not be blank!";
    public static final int CONTENT_MIN_LENGTH = 50;
    public static final int CONTENT_MAX_LENGTH = 1000;
    public static final String CONTENT_LENGTH_MESSAGE = "Content should be between " + CONTENT_MIN_LENGTH + " and " + CONTENT_MAX_LENGTH + " characters";


    // blog comment validation
    public static final String COMMENT_NOT_BLANK_MESSAGE = "Comment should not be blank!";
    public static final int COMMENT_MIN_LENGTH = 2;
    public static final int COMMENT_MAX_LENGTH = 250;
    public static final String COMMENT_LENGTH_MESSAGE = "Comment should be between " + COMMENT_MIN_LENGTH + " and " + COMMENT_MAX_LENGTH + " characters";


    // blog tag validation
    public static final String TAG_NOT_BLANK_MESSAGE = "Tags should not be blank!";
    public static final int TAG_MIN_LENGTH = 2;
    public static final int TAG_MAX_LENGTH = 250;
    public static final String TAG_LENGTH_MESSAGE = "Tags should be between " + TAG_MIN_LENGTH + " and " + TAG_MAX_LENGTH + " characters";
}
