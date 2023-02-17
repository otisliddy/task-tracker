package otisliddy.tasktracker.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(UUID id){
        super("Task id=" + id + " not found.");
    }
}
