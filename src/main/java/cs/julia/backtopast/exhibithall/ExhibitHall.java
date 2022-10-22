package cs.julia.backtopast.exhibithall;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EXHIBIT_HALL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ExhibitHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "hall_id", nullable = false)
    private int hallId;

    @Column(name = "exhibit_id", nullable = false)
    private int exhibitId;

    @Column(name = "moving_date", nullable = false)
    private Timestamp movingDate;
}
