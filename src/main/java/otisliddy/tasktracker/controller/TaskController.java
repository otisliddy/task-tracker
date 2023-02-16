package otisliddy.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import otisliddy.tasktracker.service.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/tasks/{id}")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping("/performed")
    public ResponseEntity<Void> taskPerformed(@PathVariable("id") UUID id, @RequestParam("durationMillis") Long duration) {
        service.handleTaskPerformed(id, duration);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
