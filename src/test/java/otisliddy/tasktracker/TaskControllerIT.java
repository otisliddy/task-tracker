package otisliddy.tasktracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integration.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskControllerIT {

    private static final String PATH_TASK_PERFORMED = "/v1/tasks/{id}/performed";
    private static final String QUERY_PARAM_DURATION = "durationMillis";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void taskPerformed() {
        UUID id = UUID.randomUUID();
        Long duration = 10L;

        String uri = UriComponentsBuilder.fromUriString(PATH_TASK_PERFORMED)
                .queryParam(QUERY_PARAM_DURATION, duration)
                .build()
                .toUriString();

        webTestClient.post()
                .uri(uri, id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(10L), Long.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
