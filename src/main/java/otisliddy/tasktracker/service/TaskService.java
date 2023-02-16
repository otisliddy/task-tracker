package otisliddy.tasktracker.service;

import org.springframework.stereotype.Service;
import otisliddy.tasktracker.model.Task;
import otisliddy.tasktracker.storage.TaskRepository;

import java.util.UUID;

@Service
public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void handleTaskPerformed() {
        Task task = new Task(UUID.randomUUID());
        repository.save(task);
    }

}
