package otisliddy.tasktracker;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI taskTrackerOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Task Tracker API")
                        .description("API that records and stores task time metrics.")
                        .contact(new Contact()
                                .name("Otis Liddy")
                                .url("https://github.com/otisliddy")
                                .email("otisliddy@gmail.com")));
    }
}
