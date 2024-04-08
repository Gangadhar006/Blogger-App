package org.blogger.bloggerapp.constants;

public class LoggingConstants {
    public static final String LOGGING_POINTCUT_PATH="execution(* org.blogger.bloggerapp.serviceImpl.*.*(..))";
    public static final String LOGGING_POINTCUT_NAME="logging()";
    public static final String METHOD_ENTRY="Entering method {} in class {}";
    public static final String METHOD_EXIT="Exiting method {} in class {}";
}
