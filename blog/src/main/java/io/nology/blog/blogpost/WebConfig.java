package io.nology.blog.blogpost;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// By implementing WebMvcConfigurer, we can customize the default configuration of Spring MVC.
public class WebConfig implements WebMvcConfigurer {

    @Override
    // @NonNull is not required, but can avoid NullPointerException.
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")  
        // Allow the following origins.
                .allowedOrigins("http://localhost:3000", "http://localhost:5173") 
                // Allow the following methods.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  
                // Allow the following headers.
                .allowedHeaders("*")
                // Allow credentials. (Credentials are cookies, authorization headers, or TLS client certificates.) 
                .allowCredentials(true);  
    }
}