package otisliddy.tasktracker.storage;

import org.springframework.data.repository.CrudRepository;
import otisliddy.tasktracker.model.Task;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {
}
