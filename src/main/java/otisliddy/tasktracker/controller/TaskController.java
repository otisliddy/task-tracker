package otisliddy.tasktracker.controller;

import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TaskController {


    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private static final long MIN_DURATION = 0;

    @Autowired
    private TaskService service;

    @PostMapping("/performed")
    public ResponseEntity<Void> taskPerformed(@PathVariable("id") UUID id,
                                              @RequestParam("durationMillis") @Min(MIN_DURATION) Long duration) {
        service.handleTaskPerformed(id, duration);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/averageDuration")
    public ResponseEntity<Long> getAverageDuration(@PathVariable("id") UUID id) {
        Long averageDuration = service.getAverageDuration(id);

        return ResponseEntity.ok(averageDuration);
    }

}
