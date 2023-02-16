package otisliddy.tasktracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Task implements Serializable {

    @Id
    private UUID id;

    public Task() {}

    public Task(UUID id) {
        this.id = id;
    }

}
