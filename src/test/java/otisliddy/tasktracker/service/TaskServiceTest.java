package otisliddy.tasktracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import otisliddy.tasktracker.exception.TaskNotFoundException;
import otisliddy.tasktracker.model.Task;
import otisliddy.tasktracker.storage.TaskRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository repository;
    @InjectMocks
    private TaskService service;

    @Captor
    private ArgumentCaptor<Task> taskCaptor;
    private UUID id = UUID.randomUUID();

    @Test
    void handleTaskPerformed_notInDatabase() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        service.handleTaskPerformed(id, 100L);

        verify(repository).save(taskCaptor.capture());
        assertEquals(id, taskCaptor.getValue().getId());
        assertEquals(100L, taskCaptor.getValue().getAverageDuration());
    }

    @Test
    void handleTaskPerformed_alreadyInDatabase() {
        Task task = new Task(id, 100L);
        when(repository.findById(id)).thenReturn(Optional.of(task));

        service.handleTaskPerformed(id, 200L);

        verify(repository).save(taskCaptor.capture());
        assertEquals(id, taskCaptor.getValue().getId());
        assertEquals(150L, taskCaptor.getValue().getAverageDuration());
    }

    @Test
    void getAverageDuration_notInDatabase() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> service.getAverageDuration(id));
    }

    @Test
    void getAverageDuration_inDatabase() {
        Task task = new Task(id, 100L);
        when(repository.findById(id)).thenReturn(Optional.of(task));

        Long averageDuration = service.getAverageDuration(id);

        assertEquals(100L, task.getAverageDuration());
    }
}
