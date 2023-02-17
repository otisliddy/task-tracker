package otisliddy.tasktracker.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import otisliddy.tasktracker.model.Task;

import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {
}
