package otisliddy.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import otisliddy.tasktracker.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping("/v1/task/performed")
    public ResponseEntity<Void> taskPerformed() {
        service.handleTaskPerformed();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }
}
