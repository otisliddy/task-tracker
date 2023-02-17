package otisliddy.tasktracker.model;

import com.google.common.annotations.VisibleForTesting;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Task implements Serializable {

    @Id
    private UUID id;
    private Long sumDurations = 0L;
    private Integer count = 1;

    public Task() {}

    public Task(UUID id, Long duration) {
        this.id = id;
        this.sumDurations = duration;
    }

    public void addDuration(Long duration) {
        this.sumDurations += duration;
        count++;
    }

    public Long getAverageDuration() {
        return sumDurations / count;
    }

    @VisibleForTesting
    public UUID getId() {
        return id;
    }
}
