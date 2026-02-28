package cl.thomas.mojitosecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeamos la URL /assets/image/** a la carpeta física
        registry.addResourceHandler("/assets/image/**")
                .addResourceLocations("file:src/main/resources/static/assets/image/");
    }
}