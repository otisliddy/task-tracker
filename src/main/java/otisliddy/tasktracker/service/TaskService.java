package otisliddy.tasktracker.service;

import org.springframework.stereotype.Service;
import otisliddy.tasktracker.exception.TaskNotFoundException;
import otisliddy.tasktracker.model.Task;
import otisliddy.tasktracker.storage.TaskRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void handleTaskPerformed(UUID id, Long duration) {
        Optional<Task> taskOptional = repository.findById(id);

        Task task = taskOptional.orElse(new Task(id));
        task.addDuration(duration);

        repository.save(task);
    }

    public Long getAverageDuration(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id))
                .getAverageDuration();
    }

}
