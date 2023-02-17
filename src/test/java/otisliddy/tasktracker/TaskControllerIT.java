package otisliddy.tasktracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import otisliddy.tasktracker.model.Task;
import otisliddy.tasktracker.storage.TaskRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integration.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskControllerIT {

    private static final String PATH_TASK_PERFORMED = "/v1/tasks/{id}/performed";
    private static final String PATH_AVERAGE_DURATION = "/v1/tasks/{id}/averageDuration";
    private static final String QUERY_PARAM_DURATION = "durationMillis";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TaskRepository repository;

    @Test
    void taskPerformed() {
        UUID id = UUID.randomUUID();
        String uri = buildTaskPerformedUri(10L);

        webTestClient.post()
                .uri(uri, id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        Task task = repository.findById(id).get();
        assertEquals(10L, task.getAverageDuration());
    }

    @Test
    void taskPerformed_multipleTimes() {
        UUID id = UUID.randomUUID();
        String uri = buildTaskPerformedUri(8L);

        webTestClient.post()
                .uri(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        uri = buildTaskPerformedUri(12L);
        webTestClient.post()
                .uri(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        Task task = repository.findById(id).get();
        assertEquals(10L, task.getAverageDuration());
    }

    @Test
    void taskPerformed_durationMoreThanMaxInt() {
        UUID id = UUID.randomUUID();
        Long moreThanMaxInt = Long.valueOf(Integer.MIN_VALUE) + 10L;
        String uri = buildTaskPerformedUri(moreThanMaxInt);

        webTestClient.post()
                .uri(uri, id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(moreThanMaxInt), Long.class)
                .exchange()
                .expectStatus()
                .isOk();

        Task task = repository.findById(id).get();
        assertEquals(moreThanMaxInt, task.getAverageDuration());
    }

    @Test
    void averageDuration() {
        UUID id = UUID.randomUUID();
        repository.save(new Task(id, 10L));

        webTestClient.get()
                .uri(PATH_AVERAGE_DURATION, id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Long.class)
                .isEqualTo(10L);
    }

    @Test
    void averageDuration_notFound() {
        UUID id = UUID.randomUUID();

        webTestClient.get()
                .uri(PATH_AVERAGE_DURATION, id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody()
                .jsonPath("$")
                .value(containsString(id.toString()));
    }

    private String buildTaskPerformedUri(Long duration) {
        return UriComponentsBuilder.fromUriString(PATH_TASK_PERFORMED)
                .queryParam(QUERY_PARAM_DURATION, duration)
                .build()
                .toUriString();
    }

}
