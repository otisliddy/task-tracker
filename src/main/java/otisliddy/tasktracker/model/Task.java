package otisliddy.tasktracker.model;

import com.google.common.annotations.VisibleForTesting;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents a MySQL {@code Entity} with columns:
 * <ul>
 *     <li>id VARCHAR(36) NOT NULL</li>
 *     <li>`count` INT(255) NOT NULL</li>
 *     <li>sumDurations BIGINT(255) NOT NULL</li>
 * </ul>
 * <p>
 * Rather than exposing `count` and sumDurations, this class just exposes what the columns serve to calculate: task average duration.
 */
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
        double beforeRounding = (1.0 * sumDurations) / count;
        return Math.round(beforeRounding);
    }

    @VisibleForTesting
    public UUID getId() {
        return id;
    }
}
