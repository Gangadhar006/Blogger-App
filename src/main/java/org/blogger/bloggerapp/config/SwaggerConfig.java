package org.blogger.bloggerapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import static org.blogger.bloggerapp.constants.SwaggerConstants.*;

@OpenAPIDefinition(info = @Info(title = API_TITLE, version = API_VERSION, description = API_DESCRIPTION))
//@SecurityScheme(
//        name = SECURITY_SCHEME_NAME,
//        type = SecuritySchemeType.HTTP,
//        scheme = BEARER,
//        bearerFormat = SECURITY_BEARER_FORMAT
//)
public class SwaggerConfig {

}