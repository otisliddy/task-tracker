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

    private Integer count = 1;

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getSumDurations() {
        return sumDurations;
    }

    public void addDuration(Long duration) {
        this.sumDurations += duration;
    }

    public Integer getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }
}
