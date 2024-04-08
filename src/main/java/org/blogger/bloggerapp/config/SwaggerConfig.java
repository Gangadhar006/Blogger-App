package org.blogger.bloggerapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import static org.blogger.bloggerapp.constants.SwaggerConstants.*;
import static org.blogger.bloggerapp.constants.SecurityConstants.*;

@OpenAPIDefinition(info = @Info(title = API_TITLE, version = API_VERSION, description = API_DESCRIPTION))
@SecurityScheme(
        name = SECURITY_SCHEME_NAME,
        type = SecuritySchemeType.HTTP,
        scheme = BEARER,
        bearerFormat = SECURITY_BEARER_FORMAT
)
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
public class SwaggerConfig {

}