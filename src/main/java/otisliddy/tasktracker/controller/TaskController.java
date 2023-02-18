package otisliddy.tasktracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import otisliddy.tasktracker.service.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/tasks/{id}")
@Validated
@Tag(name = "Tasks")
public class TaskController {

    private static final long MIN_DURATION = 0;

    @Autowired
    private TaskService service;

    @PostMapping("/performed")
    @Operation(summary = "Register metrics for a task that was performed.")
    public ResponseEntity<Void> taskPerformed(@PathVariable("id") @Parameter(description = "ID of the task.") UUID id,
                                              @RequestParam("durationMillis") @Min(MIN_DURATION)
                                              @Parameter(description = "Duration in milliseconds the task took to complete.") Long duration) {
        service.handleTaskPerformed(id, duration);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/averageDuration")
    @Operation(summary = "Retrieve the average duration in milliseconds that a task took to perform.")
    public ResponseEntity<Long> getAverageDuration(@PathVariable("id") @Parameter(description = "ID of the task.") UUID id) {
        Long averageDuration = service.getAverageDuration(id);

        return ResponseEntity.ok(averageDuration);
    }
}
