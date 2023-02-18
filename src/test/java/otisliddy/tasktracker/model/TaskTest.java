package otisliddy.tasktracker.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskTest {

    private UUID id = UUID.randomUUID();

    @Test
    void emptyConstructor() {
        Task task = new Task();

        assertNull(task.getId());
        assertEquals(0, task.getAverageDuration());
    }

    @Test
    void constructorWithIdAndDuration() {
        Task task = new Task(id, 5L);

        assertEquals(id, task.getId());
        assertEquals(5L, task.getAverageDuration());
    }

    @Test
    void addDuration() {
        Task task = new Task(id, 6L);
        task.addDuration(7L);
        task.addDuration(8L);
        task.addDuration(9L);
        task.addDuration(10L);

        assertEquals(8L, task.getAverageDuration());
    }

    @Test
    void addDuration_rounding() {
        Task task = new Task(id, 1L);
        task.addDuration(1L);
        task.addDuration(2L);
        assertEquals(1L, task.getAverageDuration()); // unrounded = 1.33

        task.addDuration(3L);
        assertEquals(2L, task.getAverageDuration()); // unrounded = 1.75
    }
}
