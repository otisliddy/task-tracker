package otisliddy.tasktracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Task implements Serializable {

    @Id
    private UUID id;
    private Long sumDurations = 0L;
    private Integer count = 0;

    public Task() {}

    public Task(UUID id) {
        this.id = id;
    }

    public Task(UUID id, Long duration) {
        this.id = id;
        addDuration(duration);
    }

    public void addDuration(Long duration) {
        this.sumDurations += duration;
        count++;
    }

    public Long getAverageDuration() {
        return sumDurations / count;
    }
}
